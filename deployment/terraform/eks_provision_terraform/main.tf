# Copyright (c) HashiCorp, Inc.
# SPDX-License-Identifier: MPL-2.0

# updated by using spot instance on t2.small and using the specified ubuntu image 

provider "aws" {
  region = var.region
}

# Filter out local zones, which are not currently supported 
# with managed node groups
data "aws_availability_zones" "available" {
  filter {
    name   = "opt-in-status"
    values = ["opt-in-not-required"]
  }
}

#This block defines local values that can be referenced elsewhere in the configuration.
locals {
  cluster_name = "retinal-image-api-eks-cluster"  
  instance_type = ["t2.small"]
  
}

resource "random_string" "suffix" {
  length  = 8
  special = false
}

#This block specifies the VPC module to create a Virtual Private Cloud (VPC) in AWS.
#The module provisions a VPC with specified CIDR block and subnets across multiple availability zones.
#It also enables NAT Gateway, DNS hostnames, and tags the subnets.
module "vpc" {  
  source  = "terraform-aws-modules/vpc/aws"
  version = "5.8.1"

  name = "retinal-api-backend-vpc"  

  cidr = "10.0.0.0/16"
  azs  = slice(data.aws_availability_zones.available.names, 0, 2) # from 3 to 2 due to us-west-1  only return two zones

  private_subnets = ["10.0.1.0/24", "10.0.2.0/24"] #, "10.0.3.0/24"]
  public_subnets  = ["10.0.4.0/24", "10.0.5.0/24"] #, "10.0.6.0/24"]

  enable_nat_gateway   = true
  single_nat_gateway   = true
  enable_dns_hostnames = true

  public_subnet_tags = {
    "kubernetes.io/role/elb" = 1
  }

  private_subnet_tags = {
    "kubernetes.io/role/internal-elb" = 1
  }
}

#This block specifies the EKS module to create an Amazon EKS cluster.
#It configures the cluster with the specified name, version, and endpoint access.
#The module also defines EKS managed node groups with specified instance types, sizes, and configurations.
module "eks" {
  source  = "terraform-aws-modules/eks/aws"
  version = "20.8.5"

  cluster_name    = local.cluster_name
  cluster_version = "1.29"

  cluster_endpoint_public_access           = true
  enable_cluster_creator_admin_permissions = true

  cluster_addons = {
    aws-ebs-csi-driver = {
      service_account_role_arn = module.irsa-ebs-csi.iam_role_arn
    }
  }

  vpc_id     = module.vpc.vpc_id
  subnet_ids = module.vpc.private_subnets

  eks_managed_node_group_defaults = {
    ami_type = "AL2_x86_64"  # https://docs.aws.amazon.com/eks/latest/APIReference/API_Nodegroup.html#AmazonEKS-Type-Nodegroup-amiType
  
  }

  eks_managed_node_groups = {
    one = {
      name = "worker-node-group-1"
      instance_types = local.instance_type  #["t2.small"]
      
      capacity_type  = "SPOT"
      ami_id="ami-08012c0a9ee8e21c4"
      min_size     = 1
      max_size     = 3
      desired_size = 2
    }

    two = {
      name = "worker-node-group-2"
      instance_types = local.instance_type #["t2.small"]
      
      capacity_type  = "SPOT"
      ami_id="ami-08012c0a9ee8e21c4"
      min_size     = 1
      max_size     = 2
      desired_size = 1
    }
  }
}


# https://aws.amazon.com/blogs/containers/amazon-ebs-csi-driver-is-now-generally-available-in-amazon-eks-add-ons/ 
#This line retrieves the IAM policy for the Amazon EBS CSI Driver.
#The policy is used in the IAM role module to grant permissions to the EBS CSI Controller Service Account.
data "aws_iam_policy" "ebs_csi_policy" {
  arn = "arn:aws:iam::aws:policy/service-role/AmazonEBSCSIDriverPolicy"
}

#This block specifies the IAM module to create an IAM role that can be assumed by a service account in Kubernetes.
#It creates a role named "AmazonEKSTFEBSCSIRole-<cluster-name>" with permissions specified by the EBS CSI policy.
# The role is associated with the OIDC provider for the EKS cluster and can be assumed by the EBS CSI Controller Service Account.
module "irsa-ebs-csi" {
  source  = "terraform-aws-modules/iam/aws//modules/iam-assumable-role-with-oidc"
  version = "5.39.0"

  create_role                   = true
  role_name                     = "AmazonEKSTFEBSCSIRole-${module.eks.cluster_name}"
  provider_url                  = module.eks.oidc_provider
  role_policy_arns              = [data.aws_iam_policy.ebs_csi_policy.arn]
  oidc_fully_qualified_subjects = ["system:serviceaccount:kube-system:ebs-csi-controller-sa"]
}

# Provision an EKS Cluster
Usage:
    Terraform configuration files to provision an EKS cluster on AWS with special settings as:
    a. worker nodes using spot instance on "t2.small" 
    b. worker nodes using the specified ubuntu ami: "ami-08012c0a9ee8e21c4"
    c. region using "us-west-1" 
    d. store terraform state file in backend "s3" and leveraging dynamodb for locking.
    
Steps:
    terraform init
    terraform plan -out=tfplan
    terraform validate
    terraform apply "tfplan"
    terraform destroy



 
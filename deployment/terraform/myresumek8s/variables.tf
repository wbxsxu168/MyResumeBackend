variable "cli_usr_access_key" {
  type    = string
  default = "XXXXXXXXXXXXXXXXX"             # specify the access key here
}
variable "cli_usr_secret_key" {
  type    = string
  default = "XXXXXXXXXXXXXXXXXXXXXXXXXX"    # specify the secret key here
}
variable "region" {
  type    = string
  default = "us-east-2" # specify the region
}
variable "tags" {
  type        = map(string)
  description = "mapping of tags to assign to the instance"
  default = {
    terraform = "true"
    Name      = "myresumek8s-terraform-instance"
  }
}
variable "key_name" {
  type        = string
  description = "ssh key name used to connect to the AWS EC2 instance"
  default     = "my_aws" # specify the login key name
}
variable "instance_type" {
  type        = string
  description = "specify the instance type of the AWS EC2 instance"
  default     = "t2.micro" #   instance type: t2.micro  FreeTier
}
variable "ami_id" {
  type        = string
  description = "Canonical, Ubuntu, 22.04 LTS, amd64 jammy image build on 2023-05-16"
  default     = "ami-024e6efaf93d85776" # specify the image id
}
variable "subnet_id" {
  type        = string
  description = "subnet id to launch the instance in"
  default     = "subnet-006b5345e654b53cb" # specify the available zone ID
}
variable "vpc_id" {
  type        = string
  description = "Default VPC"
  default     = "vpc-062535b233a30992c" # specify the vpc id
}
variable "availability_zone" {
  type        = string
  description = "AvailableZone to launch the instance"
  default     = "us-east-2b" # specify the available zone ID here
}
variable "instance_count" {
  type        = number
  description = "Total instance numbers configuration."
  default     = 1     # number of instances to be launched, specified here.
}

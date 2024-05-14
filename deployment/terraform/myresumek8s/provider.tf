provider "aws" {
  region = var.region
  access_key = var.cli_usr_access_key #best practice is using "aws configure" , instead of storing the key here
  secret_key = var.cli_usr_secret_key
}

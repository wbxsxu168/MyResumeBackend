terraform {
  backend "s3" {
    bucket = "yourownname**-terraform-state-bucket"
    key    = "retinalapi/staging/terraform.tfstat"
    region = "us-west-1"
    dynamodb_table = "yourowname**_tf_state_locking"
  }
}

 
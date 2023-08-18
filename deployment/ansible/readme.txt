step1: cd deployment/terraform
terraform init 
terraform plan
terraform apply --auto-approve

step2: cd deployment/ansible 
ansible-playbook deploy_myresumek8s_ubuntu.yaml

step3:
# free resource as needed
terraform destroy --auto-approve

reference: 
https://examples.javacodegeeks.com/deploying-website-on-aws-ec2-using-terraform/#google_vignette
https://dev.to/mariehposa/how-to-deploy-an-application-to-aws-ec2-instance-using-terraform-and-ansible-3e78

Notes: sometimes this one may need something as : sudo chmod 666 /var/run/docker.sock

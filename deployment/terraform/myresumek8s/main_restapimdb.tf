# creating instance
resource "aws_instance" "myresumek8s_inst" {
  ami                         = var.ami_id
  availability_zone           = var.availability_zone
  instance_type               = var.instance_type
  key_name                    = var.key_name
  count                       = var.instance_count
  security_groups             = ["${aws_security_group.ec2-sg.id}"]
  subnet_id                   = var.subnet_id
  user_data                   = file("pre_setupmyresumek8s.sh")
  associate_public_ip_address = true
 # ebs_block_device {
 #   device_name           = "/dev/xvda"
 #   volume_size           = 8
 #   volume_type           = "gp2"
 #   delete_on_termination = true
 #   tags = {
 #     Name = "terraform-storage"
 #   }
 # }

  tags = var.tags
}

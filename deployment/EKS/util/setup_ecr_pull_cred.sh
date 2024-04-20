#!/bin/bash

# Step 1: Get AWS ECR login password
AWS_REGION="us-west-1"
AWS_PASSWORD=$(aws ecr get-login-password --region $AWS_REGION)

# Step 2: Create Kubernetes secret for ECR image pulling
kubectl create secret docker-registry myecr-registry-secret \
    --docker-server=1************.dkr.ecr.us-west-1.amazonaws.com \      
    --docker-username=XXX           \
    --docker-password=$AWS_PASSWORD \
    --docker-email=xxx@xxxx.xxx
helm template myresumek8s-chart
helm package myresumek8s-chart --debug
helm install "myresumek8s" myresumek8s-chart-0.1.0.tgz --debug
kubectl get svc --watch

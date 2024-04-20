helm repo add grafana https://grafana.github.io/helm-charts
helm repo update
helm install grafana grafana/grafana      \
    --namespace monitor                   \
    --set      adminPassword='xxxxxxxxxx' \
    --values   grafana_val.yaml           \
    --set      service.type=LoadBalancer

<<comment
Its output like this:

NAME: grafana
LAST DEPLOYED: Mon Apr 15 18:49:34 2024
NAMESPACE: monitor
STATUS: deployed
REVISION: 1
NOTES:
1. Get your 'admin' user password by running:

   kubectl get secret --namespace monitor grafana -o jsonpath="{.data.admin-password}" | base64 --decode ; echo

2. The Grafana server can be accessed via port 80 on the following DNS name from within your cluster:

   grafana.monitor.svc.cluster.local     # input http://grafana.monitor.svc.cluster.local in the grafana data source->prometheus settings

   Get the Grafana URL to visit by running these commands in the same shell:
   NOTE: It may take a few minutes for the LoadBalancer IP to be available.
        You can watch the status of by running 'kubectl get svc --namespace monitor -w grafana'
     export SERVICE_IP=$(kubectl get svc --namespace monitor grafana -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
     http://$SERVICE_IP:80

3. Login with the password from step 1 and the username: admin
#################################################################################
######   WARNING: Persistence is disabled!!! You will lose your data when   #####
######            the Grafana pod is terminated.                            #####
#################################################################################
you may enable persistent storage to avoid above warning
comment

myapp % kubectl get pods -A    
<<comment
sample trace:               
NAMESPACE     NAME                                                    READY   STATUS    RESTARTS   AGE
kube-system   aws-node-ch9lr                                          2/2     Running   0          112m
kube-system   aws-node-wz6fx                                          2/2     Running   0          112m
kube-system   coredns-7fc127d7cd-bv9t5                                1/1     Running   0          118m
kube-system   coredns-7fc127d7cd-dckkp                                1/1     Running   0          118m
kube-system   ebs-csi-controller-58b128cf74-6xgvv                     6/6     Running   0          5m49s
kube-system   ebs-csi-controller-58b128cf74-pdvdz                     6/6     Running   0          5m49s
kube-system   ebs-csi-node-4gxqz                                      3/3     Running   0          5m49s
kube-system   ebs-csi-node-8mzft                                      3/3     Running   0          5m49s
kube-system   kube-proxy-c5dts                                        1/1     Running   0          112m
kube-system   kube-proxy-hkw5r                                        1/1     Running   0          112m
kube-system   metrics-server-6d94bc8694-rlhx5                         1/1     Running   0          108m
monitor       grafana-7fd8644bcb-qdhmd                                1/1     Running   0          32s
monitor       my-prometheus-alertmanager-0                            1/1     Running   0          68m
monitor       my-prometheus-kube-state-metrics-6b5bb11975-4cfwg       1/1     Running   0          68m
monitor       my-prometheus-prometheus-node-exporter-6p9ph            1/1     Running   0          68m
monitor       my-prometheus-prometheus-node-exporter-cjhb4            1/1     Running   0          68m
monitor       my-prometheus-prometheus-pushgateway-86c6c15857-88rsd   1/1     Running   0          68m
monitor       my-prometheus-server-54ff218f6d-sg74f                   2/2     Running   0          68m
comment





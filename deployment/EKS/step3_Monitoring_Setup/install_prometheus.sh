kubectl create namespace monitor
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update
helm install my-prometheus prometheus-community/prometheus --version 25.19.1 -n monitor --set alertmanager.persistentVolume.storageClass="gp2" --set server.persistentVolume.storageClass="gp2"

<<comment
NAME: my-prometheus
LAST DEPLOYED: Mon Apr 15 17:02:45 2024
NAMESPACE: monitor
STATUS: deployed
REVISION: 1
TEST SUITE: None
NOTES:
The Prometheus server can be accessed via port 80 on the following DNS name from within your cluster:
my-prometheus-server.monitor.svc.cluster.local


Get the Prometheus server URL by running these commands in the same shell:
  export POD_NAME=$(kubectl get pods --namespace monitor -l "app.kubernetes.io/name=prometheus,app.kubernetes.io/instance=my-prometheus" -o jsonpath="{.items[0].metadata.name}")
  kubectl --namespace monitor port-forward $POD_NAME 9090


The Prometheus alertmanager can be accessed via port 9093 on the following DNS name from within your cluster:
my-prometheus-alertmanager.monitor.svc.cluster.local


Get the Alertmanager URL by running these commands in the same shell:
  export POD_NAME=$(kubectl get pods --namespace monitor -l "app.kubernetes.io/name=alertmanager,app.kubernetes.io/instance=my-prometheus" -o jsonpath="{.items[0].metadata.name}")
  kubectl --namespace monitor port-forward $POD_NAME 9093
#################################################################################
######   WARNING: Pod Security Policy has been disabled by default since    #####
######            it deprecated after k8s 1.25+. use                        #####
######            (index .Values "prometheus-node-exporter" "rbac"          #####
###### .          "pspEnabled") with (index .Values                         #####
######            "prometheus-node-exporter" "rbac" "pspAnnotations")       #####
######            in case you still need it.                                #####
#################################################################################


The Prometheus PushGateway can be accessed via port 9091 on the following DNS name from within your cluster:
my-prometheus-prometheus-pushgateway.monitor.svc.cluster.local


Get the PushGateway URL by running these commands in the same shell:
  export POD_NAME=$(kubectl get pods --namespace monitor -l "app=prometheus-pushgateway,component=pushgateway" -o jsonpath="{.items[0].metadata.name}")
  kubectl --namespace monitor port-forward $POD_NAME 9091

For more information on running Prometheus, visit:
https://prometheus.io/
comment

myapp % kubectl get svc -n monitor
<<comment      
sample trace:
NAME                                     TYPE           CLUSTER-IP       EXTERNAL-IP                                                               PORT(S)        AGE
grafana                                  LoadBalancer   10.100.157.193   a640xxxxxxxxxxxxxxxxxxxxxxxxxxx-18xxxxx0653.us-west-1.elb.amazonaws.com   80:32552/TCP   111s
my-prometheus-alertmanager               ClusterIP      10.100.63.54     <none>                                                                    9093/TCP       69m
my-prometheus-alertmanager-headless      ClusterIP      None             <none>                                                                    9093/TCP       69m
my-prometheus-kube-state-metrics         ClusterIP      10.100.183.186   <none>                                                                    8080/TCP       69m
my-prometheus-prometheus-node-exporter   ClusterIP      10.100.189.190   <none>                                                                    9100/TCP       69m
my-prometheus-prometheus-pushgateway     ClusterIP      10.100.244.5     <none>                                                                    9091/TCP       69m
my-prometheus-server                     ClusterIP      10.100.22.201    <none>                                                                    80/TCP         69m
  
Suns-MacBook-Air myapp % kubectl get pvc -n monitor
NAME                                   STATUS   VOLUME                                     CAPACITY   ACCESS MODES   STORAGECLASS   VOLUMEATTRIBUTESCLASS   AGE
my-prometheus-server                   Bound    pvc-57b11f9e-0d12-40c4-bcf4-xxxxxxxxxxxx   8Gi        RWO            gp2            <unset>                 73m
storage-my-prometheus-alertmanager-0   Bound    pvc-f08e2wb3-7b4e-4f2c-82d6-xxxxxxxxxxxx   2Gi        RWO            gp2            <unset>                 112m
comment


#Notes:
#EKS use gp3 storage class

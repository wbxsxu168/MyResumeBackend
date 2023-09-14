step1: remove previous deployment by running ./uninstall.sh

step2: When using  minikube alike K8S. Unlike AWS or GCP,
it needs to launch Minikube tunnel in another console( do not close it when the APP was running), for load balancer service to bind external IP.
example:
sunxu@Suns-iMac ~ % minikube tunnel
✅  Tunnel successfully started

step3: re-deployment: kubectl create -f deployment.yaml

step4: check service running status as:
sunxu@Suns-iMac K8S % kubectl get all
NAME                              READY   STATUS    RESTARTS   AGE
pod/myresumeapp-cfddc6d78-f4s4t   1/1     Running   0          18m
pod/myresumeapp-cfddc6d78-xk5mf   1/1     Running   0          18m

NAME                 TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
service/kubernetes   ClusterIP      10.96.0.1        <none>        443/TCP          168d
service/lb-service   LoadBalancer   10.104.195.150   127.0.0.1     8080:32270/TCP   18m

NAME                          READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/myresumeapp   2/2     2            2           18m

NAME                                    DESIRED   CURRENT   READY   AGE
replicaset.apps/myresumeapp-cfddc6d78   2         2         2       18m
sunxu@Suns-iMac K8S % 

step5: Check running POD as needed:
kubectl exec --stdin --tty myresumeapp-cfddc6d78-f4s4t  -- /bin/bash

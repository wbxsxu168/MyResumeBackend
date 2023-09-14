Installation:
    step1: run command as: ./install.sh to install the myresumek8s backend using helm.

Uninstallation:
    step1: 
    helm delete myresumek8s

Installation trace example:
sunxu@Suns-iMac helm % ./install.sh                    
---
# Source: myresumek8s-chart/templates/serviceaccount.yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: release-name-myresumek8s-chart
  labels:
    helm.sh/chart: myresumek8s-chart-0.1.0
    app.kubernetes.io/name: myresumek8s-chart
    app.kubernetes.io/instance: release-name
    app.kubernetes.io/version: "1.16.0"
    app.kubernetes.io/managed-by: Helm
---
# Source: myresumek8s-chart/templates/service.yaml
apiVersion: v1
kind: Service
metadata:
  name: release-name-myresumek8s-chart
  labels:
    helm.sh/chart: myresumek8s-chart-0.1.0
    app.kubernetes.io/name: myresumek8s-chart
    app.kubernetes.io/instance: release-name
    app.kubernetes.io/version: "1.16.0"
    app.kubernetes.io/managed-by: Helm
spec:
  type: LoadBalancer
  ports:
    - port: 8086
      targetPort: 8000
      protocol: TCP
      name: http
  selector:
    app.kubernetes.io/name: myresumek8s-chart
    app.kubernetes.io/instance: release-name
---
# Source: myresumek8s-chart/templates/deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: release-name-myresumek8s-chart
  labels:
    helm.sh/chart: myresumek8s-chart-0.1.0
    app.kubernetes.io/name: myresumek8s-chart
    app.kubernetes.io/instance: release-name
    app.kubernetes.io/version: "1.16.0"
    app.kubernetes.io/managed-by: Helm
spec:
  replicas: 2
  selector:
    matchLabels:
      app.kubernetes.io/name: myresumek8s-chart
      app.kubernetes.io/instance: release-name
  template:
    metadata:
      labels:
        app.kubernetes.io/name: myresumek8s-chart
        app.kubernetes.io/instance: release-name
    spec:
      imagePullSecrets:
        - name: dockerhub-creds
      serviceAccountName: release-name-myresumek8s-chart
      securityContext:
        {}
      containers:
        - name: myresumek8s-chart
          securityContext:
            {}
          image: "wbxsxu168/myresumek8s:17"
          imagePullPolicy: IfNotPresent
          ports:
            - name: http
              containerPort: 8000
              protocol: TCP
          livenessProbe:
            httpGet:
              path: /
              port: http
          readinessProbe:
            httpGet:
              path: /
              port: http
          resources:
            {}
---
# Source: myresumek8s-chart/templates/tests/test-connection.yaml
apiVersion: v1
kind: Pod
metadata:
  name: "release-name-myresumek8s-chart-test-connection"
  labels:
    helm.sh/chart: myresumek8s-chart-0.1.0
    app.kubernetes.io/name: myresumek8s-chart
    app.kubernetes.io/instance: release-name
    app.kubernetes.io/version: "1.16.0"
    app.kubernetes.io/managed-by: Helm
  annotations:
    "helm.sh/hook": test
spec:
  containers:
    - name: wget
      image: busybox
      command: ['wget']
      args: ['release-name-myresumek8s-chart:']
  restartPolicy: Never
Successfully packaged chart and saved it to: /Users/sunxu/mygitops/MyResumeBackend/deployment/helm/myresumek8s-chart-0.1.0.tgz
install.go:194: [debug] Original chart version: ""
install.go:211: [debug] CHART PATH: /Users/sunxu/mygitops/MyResumeBackend/deployment/helm/myresumek8s-chart-0.1.0.tgz

client.go:133: [debug] creating 3 resource(s)
NAME: myresumek8s
LAST DEPLOYED: Thu Sep 14 13:36:55 2023
NAMESPACE: default
STATUS: deployed
REVISION: 1
USER-SUPPLIED VALUES:
{}

COMPUTED VALUES:
affinity: {}
autoscaling:
  enabled: false
  maxReplicas: 10
  minReplicas: 1
  targetCPUUtilizationPercentage: 80
fullnameOverride: ""
image:
  pullPolicy: IfNotPresent
  repository: wbxsxu168/myresumek8s
  tag: 17
imagePullSecrets:
- name: dockerhub-creds
ingress:
  annotations: {}
  className: ""
  enabled: false
  hosts:
  - host: chart-example.local
    paths:
    - path: /
      pathType: ImplementationSpecific
  tls: []
nameOverride: ""
nodeSelector: {}
podAnnotations: {}
podSecurityContext: {}
replicaCount: 2
resources: {}
securityContext: {}
service:
  externalPort: 8086
  internalPort: 8000
  name: myresumek8s-lb
  type: LoadBalancer
serviceAccount:
  annotations: {}
  create: true
  name: ""
tolerations: []

HOOKS:
---
# Source: myresumek8s-chart/templates/tests/test-connection.yaml
apiVersion: v1
kind: Pod
metadata:
  name: "myresumek8s-myresumek8s-chart-test-connection"
  labels:
    helm.sh/chart: myresumek8s-chart-0.1.0
    app.kubernetes.io/name: myresumek8s-chart
    app.kubernetes.io/instance: myresumek8s
    app.kubernetes.io/version: "1.16.0"
    app.kubernetes.io/managed-by: Helm
  annotations:
    "helm.sh/hook": test
spec:
  containers:
    - name: wget
      image: busybox
      command: ['wget']
      args: ['myresumek8s-myresumek8s-chart:']
  restartPolicy: Never
MANIFEST:
---
# Source: myresumek8s-chart/templates/serviceaccount.yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: myresumek8s-myresumek8s-chart
  labels:
    helm.sh/chart: myresumek8s-chart-0.1.0
    app.kubernetes.io/name: myresumek8s-chart
    app.kubernetes.io/instance: myresumek8s
    app.kubernetes.io/version: "1.16.0"
    app.kubernetes.io/managed-by: Helm
---
# Source: myresumek8s-chart/templates/service.yaml
apiVersion: v1
kind: Service
metadata:
  name: myresumek8s-myresumek8s-chart
  labels:
    helm.sh/chart: myresumek8s-chart-0.1.0
    app.kubernetes.io/name: myresumek8s-chart
    app.kubernetes.io/instance: myresumek8s
    app.kubernetes.io/version: "1.16.0"
    app.kubernetes.io/managed-by: Helm
spec:
  type: LoadBalancer
  ports:
    - port: 8086
      targetPort: 8000
      protocol: TCP
      name: http
  selector:
    app.kubernetes.io/name: myresumek8s-chart
    app.kubernetes.io/instance: myresumek8s
---
# Source: myresumek8s-chart/templates/deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myresumek8s-myresumek8s-chart
  labels:
    helm.sh/chart: myresumek8s-chart-0.1.0
    app.kubernetes.io/name: myresumek8s-chart
    app.kubernetes.io/instance: myresumek8s
    app.kubernetes.io/version: "1.16.0"
    app.kubernetes.io/managed-by: Helm
spec:
  replicas: 2
  selector:
    matchLabels:
      app.kubernetes.io/name: myresumek8s-chart
      app.kubernetes.io/instance: myresumek8s
  template:
    metadata:
      labels:
        app.kubernetes.io/name: myresumek8s-chart
        app.kubernetes.io/instance: myresumek8s
    spec:
      imagePullSecrets:
        - name: dockerhub-creds
      serviceAccountName: myresumek8s-myresumek8s-chart
      securityContext:
        {}
      containers:
        - name: myresumek8s-chart
          securityContext:
            {}
          image: "wbxsxu168/myresumek8s:17"
          imagePullPolicy: IfNotPresent
          ports:
            - name: http
              containerPort: 8000
              protocol: TCP
          livenessProbe:
            httpGet:
              path: /
              port: http
          readinessProbe:
            httpGet:
              path: /
              port: http
          resources:
            {}

NOTES:
1. Get the application URL by running these commands:
     NOTE: It may take a few minutes for the LoadBalancer IP to be available.
           You can watch the status of by running 'kubectl get --namespace default svc -w myresumek8s-myresumek8s-chart'
  export SERVICE_IP=$(kubectl get svc --namespace default myresumek8s-myresumek8s-chart --template "{{ range (index .status.loadBalancer.ingress 0) }}{{.}}{{ end }}")
  echo http://$SERVICE_IP:
NAME                            TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
kubernetes                      ClusterIP      10.96.0.1        <none>        443/TCP          169d
lb-service                      LoadBalancer   10.97.202.185    127.0.0.1     8080:31435/TCP   17h
myresumek8s-myresumek8s-chart   LoadBalancer   10.104.226.184   127.0.0.1     8086:30127/TCP   0s


The MyREsumeK8S service can be accessed as : http://localhost:8086/





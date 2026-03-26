# Day 52 – Kubernetes Namespaces and Deployments

### Task 1: Explore Default Namespaces
Kubernetes comes with built-in namespaces. List them:

```bash
kubectl get namespaces
```
![alt text](images/image.png)

You should see at least:
- `default` — where your resources go if you do not specify a namespace
- `kube-system` — Kubernetes internal components (API server, scheduler, etc.)
- `kube-public` — publicly readable resources
- `kube-node-lease` — node heartbeat tracking

Check what is running inside `kube-system`:
```bash
kubectl get pods -n kube-system
```
![alt text](images/image-1.png)

These are the control plane components keeping your cluster alive. Do not touch them.

**Verify:** How many pods are running in `kube-system`?

- there is 14 pods running

---

### Task 2: Create and Use Custom Namespaces
Create two namespaces — one for a development environment and one for staging:

```bash
kubectl create namespace dev
kubectl create namespace staging
```
![alt text](images/image-2.png)


Verify they exist:
```bash
kubectl get namespaces
```

You can also create a namespace from a manifest:
```yaml
# namespace.yaml
apiVersion: v1
kind: Namespace
metadata:
  name: production
```


```bash
kubectl apply -f namespace.yaml
```

![alt text](images/image-3.png)


Now run a pod in a specific namespace:
```bash
kubectl run nginx-dev --image=nginx:latest -n dev
kubectl run nginx-staging --image=nginx:latest -n staging
```
![alt text](images/image-4.png)

List pods across all namespaces:
```bash
kubectl get pods -A
```
![alt text](images/image-5.png)

Notice that `kubectl get pods` without `-n` only shows the `default` namespace. You must specify `-n <namespace>` or use `-A` to see everything.

**Verify:** Does `kubectl get pods` show these pods? What about `kubectl get pods -A`?

- with `kubectl get pods` shows pod in only default namespace while `kubectl get pods -A` shows pods inside all namespaces

---

### Task 3: Create Your First Deployment
A Deployment tells Kubernetes: "I want X replicas of this Pod running at all times." If a Pod crashes, the Deployment controller recreates it automatically.

Create a file `nginx-deployment.yaml`:

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deployment
  namespace: dev
  labels:
    app: nginx
spec:
  replicas: 3
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx:1.24
        ports:
        - containerPort: 80
```

Key differences from a standalone Pod:
- `kind: Deployment` instead of `kind: Pod`
- `apiVersion: apps/v1` instead of `v1`
- `replicas: 3` tells Kubernetes to maintain 3 identical pods
- `selector.matchLabels` connects the Deployment to its Pods
- `template` is the Pod template — the Deployment creates Pods using this blueprint

Apply it:
```bash
kubectl apply -f nginx-deployment.yaml
```
![alt text](images/image-6.png)

Check the result:
```bash
kubectl get deployments -n dev
kubectl get pods -n dev
```
![alt text](images/image-7.png)

You should see 3 pods with names like `nginx-deployment-xxxxx-yyyyy`.

**Verify:** What do the READY, UP-TO-DATE, and AVAILABLE columns mean in the deployment output?

`READY = Running & Ready Pods / Desired Pods`
`UP-TO-DATE = This shows How many pods are running the latest deployment configuration`
`AVAILABLE = How many pods are available to serve traffic`



### Task 4: Self-Healing — Delete a Pod and Watch It Come Back
This is the key difference between a Deployment and a standalone Pod.

```bash
# List pods

kubectl get pods -n dev
```
![alt text](images/image-8.png)


```bash
# Delete one of the deployment's pods (use an actual pod name from your output)

kubectl delete pod <pod-name> -n dev
```
![alt text](images/image-9.png)


```bash
# Immediately check again

kubectl get pods -n dev
```
![alt text](images/image-10.png)

The Deployment controller detects that only 2 of 3 desired replicas exist and immediately creates a new one. The deleted pod is replaced within seconds.

**Verify:** Is the replacement pod's name the same as the one you deleted, or different?

- pod name is different 

---

### Task 5: Scale the Deployment
Change the number of replicas:

```bash
# Scale up to 5
kubectl scale deployment nginx-deployment --replicas=5 -n dev
kubectl get pods -n dev
```

![alt text](images/image-11.png)


```bash
# Scale down to 2
kubectl scale deployment nginx-deployment --replicas=2 -n dev
kubectl get pods -n dev
```
![alt text](images/image-12.png)


Watch how Kubernetes creates or terminates pods to match the desired count.

You can also scale by editing the manifest — change `replicas: 4` in your YAML file and run `kubectl apply -f nginx-deployment.yaml` again.

![alt text](images/image-13.png)

**Verify:** When you scaled down from 5 to 2, what happened to the extra pods?

- Extra pods gets start terminating after desired state changed

---


### Task 6: Rolling Update
Update the Nginx image version to trigger a rolling update:

```bash
kubectl set image deployment/nginx-deployment nginx=nginx:1.25 -n dev
```
 ![alt text](images/image-14.png)

Watch the rollout in real time:
```bash
kubectl rollout status deployment/nginx-deployment -n dev
```
![alt text](images/image-15.png)

Kubernetes replaces pods one by one — old pods are terminated only after new ones are healthy. This means zero downtime.

Check the rollout history:
```bash
kubectl rollout history deployment/nginx-deployment -n dev
```
![alt text](images/image-16.png)

Now roll back to the previous version:
```bash
kubectl rollout undo deployment/nginx-deployment -n dev
kubectl rollout status deployment/nginx-deployment -n dev
```
![alt text](images/image-17.png)

Verify the image is back to the previous version:
```bash
kubectl describe deployment nginx-deployment -n dev | grep Image
```
![alt text](images/image-18.png)

**Verify:** What image version is running after the rollback?

- 1.24 Image version running 

---

### Task 7: Clean Up
```bash
kubectl delete deployment nginx-deployment -n dev
kubectl delete pod nginx-dev -n dev
kubectl delete pod nginx-staging -n staging
kubectl delete namespace dev staging production
```
![alt text](images/image-19.png)

Deleting a namespace removes everything inside it. Be very careful with this in production.

```bash
kubectl get namespaces
kubectl get pods -A
```

![alt text](images/image-20.png)

**Verify:** Are all your resources gone?

- yes all resource gone 

---
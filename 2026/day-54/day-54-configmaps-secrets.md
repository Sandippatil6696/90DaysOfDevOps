# Day 54 – Kubernetes ConfigMaps and Secrets

### Task 1: Create a ConfigMap from Literals
1. Use `kubectl create configmap` with `--from-literal` to create a ConfigMap called `app-config` with keys `APP_ENV=production`, `APP_DEBUG=false`, and `APP_PORT=8080`

- `kubectl create configmap app-config --from-literal=APP_ENV=production --from-literal=APP_DEBUG=false --from-literal=APP_PORT=8080`

![alt text](imges/image.png)

2. Inspect it with `kubectl describe configmap app-config` and `kubectl get configmap app-config -o yaml`

![alt text](imges/image-1.png)

3. Notice the data is stored as plain text — no encoding, no encryption

**Verify:** Can you see all three key-value pairs?

- yes 

---

### Task 2: Create a ConfigMap from a File
1. Write a custom Nginx config file that adds a `/health` endpoint returning "healthy"
2. Create a ConfigMap from this file using `kubectl create configmap nginx-config --from-file=default.conf=<your-file>`
3. The key name (`default.conf`) becomes the filename when mounted into a Pod

**Verify:** Does `kubectl get configmap nginx-config -o yaml` show the file contents?

![alt text](imges/image-2.png)

---

### Task 3: Use ConfigMaps in a Pod
1. Write a Pod manifest that uses `envFrom` with `configMapRef` to inject all keys from `app-config` as environment variables. Use a busybox container that prints the values.

![alt text](imges/image-5.png)

![alt text](imges/image-3.png)

2. Write a second Pod manifest that mounts `nginx-config` as a volume at `/etc/nginx/conf.d`. Use the nginx image.

![alt text](imges/image-6.png)

![alt text](imges/image-4.png)

3. Test that the mounted config works: `kubectl exec <pod> -- curl -s http://localhost/health`

Use environment variables for simple key-value settings. Use volume mounts for full config files.

**Verify:** Does the `/health` endpoint respond?

![alt text](imges/image-7.png)

---

### Task 4: Create a Secret
1. Use `kubectl create secret generic db-credentials` with `--from-literal` to store `DB_USER=admin` and `DB_PASSWORD=s3cureP@ssw0rd`

- `kubectl create secret generic db-credentials --from-literal=DB_USER=admin --from-literal=DB_PASSWORD=s3cureP@ssw0rd`

2. Inspect with `kubectl get secret db-credentials -o yaml` — the values are base64-encoded

3. Decode a value: `echo '<base64-value>' | base64 --decode`

![alt text](imges/image-8.png)

**base64 is encoding, not encryption.** Anyone with cluster access can decode Secrets. The real advantages are RBAC separation, tmpfs storage on nodes, and optional encryption at rest.

**Verify:** Can you decode the password back to plaintext?

- yes 

---

### Task 5: Use Secrets in a Pod
1. Write a Pod manifest that injects `DB_USER` as an environment variable using `secretKeyRef`
2. In the same Pod, mount the entire `db-credentials` Secret as a volume at `/etc/db-credentials` with `readOnly: true`
3. Verify: each Secret key becomes a file, and the content is the decoded plaintext value

**Verify:** Are the mounted file values plaintext or base64?

![alt text](imges/image-9.png)


---

### Task 6: Update a ConfigMap and Observe Propagation
1. Create a ConfigMap `live-config` with a key `message=hello`

- `kubectl create configmap live-config --from-literal=message=hello`
  
![alt text](imges/image-10.png)

2. Write a Pod that mounts this ConfigMap as a volume and reads the file in a loop every 5 seconds

![alt text](imges/image-12.png)

![alt text](imges/image-11.png)

3. Update the ConfigMap: `kubectl patch configmap live-config --type merge -p '{"data":{"message":"world"}}'`

![alt text](imges/image-13.png)

4. Wait 30-60 seconds — the volume-mounted value updates automatically

- `kubectl logs pod/<pod-name>`

- yes volume-mounted value updates automatically i.e message from file i.e world  

5. Environment variables from earlier tasks do NOT update — they are set at pod startup only

- ![alt text](imges/image-14.png)

i.e mesage from env is fixed i.e hellow

**Verify:** Did the volume-mounted value change without a pod restart?

- Yes, the volume-mounted value changes automatically without restarting the Pod
- environment variables do NOT update (they are fixed at pod startup)


### Task 7: Clean Up
Delete all pods, ConfigMaps, and Secrets you created.

---
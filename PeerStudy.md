Peer Study :-

- 25 Unix/Linux Command 
- 25 Kubernetes Command
- 20 Docker Command

Repo URL : https://github.com/Luminary1992/peer-prog


Session - 1) Build a basic Spring Boot application with some REST Endpoints.
		- Build and call to endpoints. ✅
		- get metrics with prometheous.✅

Session - 2) Add Dockerfile 
		- Docker Image and Docker Containers.
		- Build the docker image and run it.
		- call the endpoints running in docker containers.
		- push the image to docker-hub with a tag.

Session - 3) Docker Compose 
		- Build the app with docker-compose.yaml file.
		- add database to application.
		- database as the docker image (Postgres).
		- app container talks to postgres container.

Session - 4) Visualization 
		- configure grafana.
		- configure prometheus.
		- catch metrics from the application and show in Grafana Dashboard.
		- Grafana and Prometheus run as the Docker Containers (Images).
		- Collects app metrics and docker metrics.


Session - 5) Kubernetes 
		- Details Cluster, Node, Service, Pod , Container, Nginx/Ingress
		- Deploy the application to pods
		- Access it with pods IP.
		- Grafana collects metrics from Kubernetes and shows them on the Dashboard.


Session - 6) CI/CD
		- Github Action
		- Build the project, create the docker image and push to docker hub.
		- pull the image from docker hub and deploy to Cluster.



Peer Study Additions:-

1) Spring Boot Application
   - Add Spring Profiles (dev, prod) to manage environment-specific configs
   - Health checks via Spring Actuator (/actuator/health) — ties directly into Docker & K8s later
   - Swagger/OpenAPI docs for the REST endpoints
   - Basic error handling (@ControllerAdvice) and input validation

2) Dockerfile
   - Multi-stage builds (compile in one stage, run in a slim JRE image) — reduces image size significantly
   - .dockerignore file
   - Difference between CMD vs ENTRYPOINT

3) Docker Compose
   - Volumes for Postgres data persistence (so data survives container restarts)
   - Health checks in docker-compose.yaml so the app waits for Postgres to be ready
   - Environment variables via .env file instead of hardcoding credentials
   - depends_on with condition (service_healthy)

4) Visualization
   - Alerting in Grafana (e.g., alert when error rate spikes)
   - cAdvisor for Docker container-level metrics (CPU, memory per container)
   - Custom application metrics using Micrometer (counters, timers, gauges)

5) Kubernetes
   - ConfigMaps & Secrets (don't hardcode DB credentials in manifests)
   - Liveness vs Readiness probes — critical concept, maps back to Actuator
   - Resource requests & limits (CPU/memory)
   - Horizontal Pod Autoscaler (HPA)
   - Namespaces for environment separation
   - PersistentVolume & PersistentVolumeClaim for Postgres in K8s
   - Helm as a bonus — package and version your K8s manifests

6) CI/CD
   - Github Action
   - Run tests in the pipeline before building the image (fail fast)
   - Image tagging strategy — use Git SHA or semver, not just latest
   - Secrets management in GitHub Actions (never hardcode credentials)
   - Add a staging → production promotion step
   - Rollback strategy in K8s (kubectl rollout undo)


7) Extra — Security Basics
   - HTTPS / TLS termination at the Ingress level
   - Non-root user in Dockerfile
   - Scanning Docker images for vulnerabilities (Trivy)
   - Network Policies in K8s to restrict pod-to-pod traffic


============================================================
Key Gaps in Current Plan:
   - Data persistence in K8s (Postgres needs PVCs)
   - Secrets management throughout all stages
   - Testing in CI before the image is built


Link : https://docs.google.com/document/d/1ZpeyxVkGPMSBVWCJ6NDFMtiEvkcitDqpQ5utapWFyR8/edit?pli=1&tab=t.0

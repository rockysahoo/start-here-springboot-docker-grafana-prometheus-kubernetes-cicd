# How to Import Grafana Dashboards

This guide will help you import the sample Grafana dashboards into your Grafana instance.

## Available Dashboards

1. **Spring Boot Application Metrics** - Monitor JVM memory, threads, and HTTP requests
2. **Kubernetes Pods Monitoring** - Monitor CPU, memory, and network usage of pods
3. **Prometheus Monitoring** - Monitor Prometheus itself

## Import Steps

### Method 1: Using Grafana UI

1. **Open Grafana Dashboard**
   - Navigate to `http://<node-ip>:30300` (or `http://localhost:30300` for Docker Desktop)
   - Login with `admin` / `admin`

2. **Go to Dashboards**
   - Click on the **Dashboards** menu on the left sidebar
   - Click **+ New** → **Import**

3. **Import JSON**
   - Choose one of the following options:
     - **Option A:** Paste the JSON content from the dashboard file
     - **Option B:** Upload the JSON file directly
     - **Option C:** Enter the dashboard ID (if published to Grafana.com)

4. **Select Data Source**
   - When prompted, select **Prometheus** as the data source
   - Click **Import**

### Method 2: Direct Dashboard Upload

If Grafana has file upload enabled:
1. Go to **Dashboards** → **Import**
2. Click **Upload JSON file**
3. Select the dashboard JSON file from `grafana-dashboards/` folder
4. Configure the data source and click **Import**

## Dashboard Details

### 1. Spring Boot Application Metrics
**File:** `spring-boot-app-dashboard.json`

**Panels:**
- JVM Memory Usage - Shows heap and non-heap memory utilization
- JVM Live Threads - Gauge showing active thread count
- HTTP Request Rate - Requests per second
- HTTP Request Latency - 95th and 99th percentile latency

**Requirements:**
- Spring Boot application with Micrometer Prometheus exporter
- Metrics exposed at `/actuator/prometheus`

### 2. Kubernetes Pods Monitoring
**File:** `kubernetes-pods-dashboard.json`

**Panels:**
- Pod CPU Usage - Per-pod CPU consumption
- Pod Memory Usage - Per-pod memory consumption
- Pod Network I/O - Network receive and transmit rates
- Pod Status - Shows if pods are running

**Requirements:**
- Kubernetes cluster with cAdvisor metrics
- kube-state-metrics deployed

### 3. Prometheus Monitoring
**File:** `prometheus-dashboard.json`

**Panels:**
- Chunks Created - Total chunks created
- Symbol Table Size - TSDB storage size
- Concurrent Reads - Number of concurrent metric reads
- Series Count - Total time series in TSDB
- HTTP Request Rate - Prometheus API request rate
- Process Memory - Resident and virtual memory of Prometheus

## Customizing Dashboards

### Add Variables for Dynamic Filtering
1. Click **Dashboard Settings** (gear icon)
2. Go to **Variables**
3. Create new variables for pod names, namespaces, etc.
4. Update panel queries to use `$variable_name`

### Modify Queries
1. Click on any panel to edit
2. Click the **Queries** tab
3. Modify the PromQL query
4. Click **Apply** or **Save**

## PromQL Query Examples

### JVM Metrics
```promql
# Memory usage
jvm_memory_used_bytes{application="peer-study-app"}

# Garbage collection
rate(jvm_gc_memory_promoted_bytes_total[5m])

# Thread count
jvm_threads_live{application="peer-study-app"}
```

### Kubernetes Metrics
```promql
# Pod CPU
rate(container_cpu_usage_seconds_total{pod=~"peer-study-.*"}[1m])

# Pod Memory
container_memory_usage_bytes{pod=~"peer-study-.*"}

# Pod Status
kube_pod_status_phase{pod=~"peer-study-.*"}
```

### HTTP Metrics
```promql
# Request rate
rate(http_requests_total[1m])

# Response time (95th percentile)
histogram_quantile(0.95, rate(http_request_duration_seconds_bucket[5m]))

# Error rate
rate(http_requests_total{status=~"5.."}[1m])
```

## Troubleshooting

### No Data Shown
1. Check that Prometheus is scraping metrics correctly
   - Visit Prometheus UI: `http://<node-ip>:30090`
   - Check **Status** → **Targets**

2. Verify the metric names match your application
   - Update panel queries with correct metric names
   - Use the Prometheus UI to explore available metrics

3. Ensure data source is connected
   - Go to **Configuration** → **Data Sources**
   - Test the Prometheus data source connection

### Metrics Not Available
- Ensure Spring Boot app has Micrometer Prometheus exporter dependency
- Check that `/actuator/prometheus` endpoint is accessible
- Verify Prometheus is configured to scrape your application

## Next Steps

1. **Create Custom Dashboards** - Combine metrics from multiple sources
2. **Set Up Alerts** - Define alert rules based on metric thresholds
3. **Share Dashboards** - Export and share dashboards with team members
4. **Explore Grafana Features** - Use annotations, heatmaps, and advanced visualizations

## Additional Resources

- [Grafana Documentation](https://grafana.com/docs/)
- [Prometheus Query Language](https://prometheus.io/docs/prometheus/latest/querying/basics/)
- [Micrometer Documentation](https://micrometer.io/docs)
- [Kubernetes Metrics](https://kubernetes.io/docs/tasks/debug-application-cluster/resource-metrics-pipeline/)


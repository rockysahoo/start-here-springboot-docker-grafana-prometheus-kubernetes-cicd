resource "grafana_rule_group" "rule_group_17475a573c57bbd1" {
  org_id           = 1
  name             = "My App API"
  folder_uid       = "afxjim5uu7yf4e"
  interval_seconds = 60

  rule {
    name      = "P2- My API Low Running Pods"
    condition = "C"

    data {
      ref_id = "A"

      relative_time_range {
        from = 600
        to   = 0
      }

      datasource_uid = "dfxjfo96ulh4wa"
      model          = "{\"editorMode\":\"code\",\"expr\":\"sum(\\r\\n  kube_pod_container_status_running{\\r\\n    container=~\\\"peer-study-app\\\"\\r\\n  }\\r\\n)\",\"instant\":true,\"intervalMs\":1000,\"legendFormat\":\"__auto\",\"maxDataPoints\":43200,\"range\":false,\"refId\":\"A\"}"
    }
    data {
      ref_id     = "C"
      query_type = "expression"

      relative_time_range {
        from = 0
        to   = 0
      }

      datasource_uid = "__expr__"
      model          = "{\"conditions\":[{\"evaluator\":{\"params\":[2],\"type\":\"lt\"},\"operator\":{\"type\":\"and\"},\"query\":{\"params\":[\"C\"]},\"reducer\":{\"params\":[],\"type\":\"last\"},\"type\":\"query\"}],\"datasource\":{\"type\":\"__expr__\",\"uid\":\"__expr__\"},\"expression\":\"A\",\"intervalMs\":1000,\"maxDataPoints\":43200,\"refId\":\"C\",\"type\":\"threshold\"}"
    }

    no_data_state   = "NoData"
    exec_err_state  = "Error"
    for             = "1m"
    keep_firing_for = "1m"
    annotations = {
      description = "CRITICAL:My API has low running pods -might be service outage."
      runbook_url = "https://www.google.com"
      summary     = "My  API has low running pods for 5 minutes."
    }
    labels = {
      environment = "dev"
      pod_count   = "zero"
      priority    = "2"
    }
    is_paused = false

    notification_settings {
      contact_point = "Biswajit Sahoo"
    }
  }
}

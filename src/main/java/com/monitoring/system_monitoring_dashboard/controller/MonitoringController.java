package com.monitoring.system_monitoring_dashboard.controller;

import com.monitoring.system_monitoring_dashboard.model.SystemMetricsDTO;
import com.monitoring.system_monitoring_dashboard.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MonitoringController exposes a REST API endpoint to provide system metrics.
 * It delegates the business logic to MonitoringService and returns a DTO as JSON.
 */
@RestController
public class MonitoringController {
    
    // Service that provides system metrics
    private final MonitoringService monitoringService;

    // Constructor injection of the service
    @Autowired
    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    /**
     * GET endpoint to retrieve current system metrics as JSON.
     * URL: /api/monitoring
     * @return SystemMetricsDTO containing CPU and RAM information
     */
    @GetMapping("/api/monitoring")
    public SystemMetricsDTO getSystemMetrics() {
        return monitoringService.getSystemMetrics();
    }
}

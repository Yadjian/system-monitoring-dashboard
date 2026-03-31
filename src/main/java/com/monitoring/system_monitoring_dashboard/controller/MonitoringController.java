package com.monitoring.system_monitoring_dashboard.controller;

import com.monitoring.system_monitoring_dashboard.model.*;
import com.monitoring.system_monitoring_dashboard.service.MonitoringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * REST controller exposing endpoints to retrieve system metrics.
 */
@RestController
public class MonitoringController {

    /** Service used to retrieve system metrics. */
    private final MonitoringService monitoringService;

    /**
     * Constructor for MonitoringController.
     * @param monitoringService Service used to retrieve system metrics.
     */
    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    /**
     * Endpoint to retrieve all system metrics (agrégé).
     */
    @GetMapping("/api/metrics")
    public AllMetricsDTO getAllMetrics() {
        return monitoringService.getAllMetrics();
    }

    /**
     * Endpoint to retrieve CPU metrics.
     * @return CpuMetricsDTO containing CPU information and usage.
     */
    @GetMapping("/api/cpu")
    public CpuMetricsDTO getCpuMetrics() {
        return monitoringService.getCpuMetrics();
    }

    /**
     * Endpoint to retrieve RAM metrics.
     * @return RamMetricsDTO containing RAM information and usage.
     */
    @GetMapping("/api/ram")
    public RamMetricsDTO getRamMetrics() {
        return monitoringService.getRamMetrics();
    }

    /**
     * Endpoint to retrieve disk metrics.
     * @return List of DiskMetricsDTO containing disk information and usage.
     */
    @GetMapping("/api/disks")
    public List<DiskMetricsDTO> getDiskMetrics() {
        return monitoringService.getDiskMetrics();
    }

    /**
     * Endpoint to retrieve GPU metrics.
     * @return GpuMetricsDTO containing GPU information.
     */
    @GetMapping("/api/gpu")
    public GpuMetricsDTO getGpuMetrics() {
        return monitoringService.getGpuMetrics();
    }
}

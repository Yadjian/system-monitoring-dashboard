package com.monitoring.system_monitoring_dashboard.model;

/**
 * Data Transfer Object (DTO) for system metrics.
 * Contains CPU and RAM information to be sent as JSON in the API response.
 */
public class SystemMetricsDTO {
    // The name of the CPU
    private String cpuName;

    // The CPU usage percentage as a formatted string
    private String cpuUsagePercent;

    // Total RAM in megabytes
    private long ramTotalMB;

    // Available RAM in megabytes
    private long ramAvailableMB;

    // Getters and setters
    public String getCpuName() { return cpuName; }
    public void setCpuName(String cpuName) { this.cpuName = cpuName; }

    public String getCpuUsagePercent() { return cpuUsagePercent; }
    public void setCpuUsagePercent(String cpuUsagePercent) { this.cpuUsagePercent = cpuUsagePercent; }

    public long getRamTotalMB() { return ramTotalMB; }
    public void setRamTotalMB(long ramTotalMB) { this.ramTotalMB = ramTotalMB; }

    public long getRamAvailableMB() { return ramAvailableMB; }
    public void setRamAvailableMB(long ramAvailableMB) { this.ramAvailableMB = ramAvailableMB; }
}

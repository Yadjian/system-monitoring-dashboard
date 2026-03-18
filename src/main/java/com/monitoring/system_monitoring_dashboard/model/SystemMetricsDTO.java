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

    private double cpuTemperature;
    public double getCpuTemperature() { return cpuTemperature; }
    public void setCpuTemperature(double cpuTemperature) { this.cpuTemperature = cpuTemperature; }

    private int cpuPhysicalCores;
    public int getCpuPhysicalCores() { return cpuPhysicalCores; }
    public void setCpuPhysicalCores(int cpuPhysicalCores) { this.cpuPhysicalCores = cpuPhysicalCores; }

    private int cpuLogicalCores;
    public int getCpuLogicalCores() { return cpuLogicalCores; }
    public void setCpuLogicalCores(int cpuLogicalCores) { this.cpuLogicalCores = cpuLogicalCores; }

    private long cpuMaxFreq;
    public long getCpuMaxFreq() { return cpuMaxFreq; }
    public void setCpuMaxFreq(long cpuMaxFreq) { this.cpuMaxFreq = cpuMaxFreq; }

    private long[] cpuCurrentFreq;
    public long[] getCpuCurrentFreq() { return cpuCurrentFreq; }
    public void setCpuCurrentFreq(long[] cpuCurrentFreq) { this.cpuCurrentFreq = cpuCurrentFreq; }
}

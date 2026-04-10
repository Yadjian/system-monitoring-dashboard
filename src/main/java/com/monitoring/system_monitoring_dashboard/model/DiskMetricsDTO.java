package com.monitoring.system_monitoring_dashboard.model;

/**
 * Data Transfer Object representing disk metrics.
 * Contains information about disk name, total, free, used space, and usage percentage.
 */
public class DiskMetricsDTO {
    private String name;
    private long totalMB;
    private long usedMB;
    private double usagePercent;
    private String type;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getTotalMB() { return totalMB; }
    public void setTotalMB(long totalMB) { this.totalMB = totalMB; }

    public long getUsedMB() { return usedMB; }
    public void setUsedMB(long usedMB) { this.usedMB = usedMB; }

    public double getUsagePercent() { return usagePercent; }
    public void setUsagePercent(double usagePercent) { this.usagePercent = usagePercent; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}

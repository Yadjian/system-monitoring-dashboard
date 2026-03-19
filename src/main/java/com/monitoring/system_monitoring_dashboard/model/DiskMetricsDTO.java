package com.monitoring.system_monitoring_dashboard.model;

/**
 * Data Transfer Object representing disk metrics.
 * Contains information about disk name, total, free, used space, and usage percentage.
 */
public class DiskMetricsDTO {
    /** Disk name or identifier. */
    private String name;
    /** Total disk space in megabytes. */
    private long totalMB;
    /** Free disk space in megabytes. */
    private long freeMB;
    /** Used disk space in megabytes. */
    private long usedMB;
    /** Disk usage percentage. */
    private double usagePercent;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getTotalMB() { return totalMB; }
    public void setTotalMB(long totalMB) { this.totalMB = totalMB; }

    public long getFreeMB() { return freeMB; }
    public void setFreeMB(long freeMB) { this.freeMB = freeMB; }

    public long getUsedMB() { return usedMB; }
    public void setUsedMB(long usedMB) { this.usedMB = usedMB; }

    public double getUsagePercent() { return usagePercent; }
    public void setUsagePercent(double usagePercent) { this.usagePercent = usagePercent; }

    private boolean critical;
    public boolean isCritical() { return critical; }
    public void setCritical(boolean critical) { this.critical = critical; }
}


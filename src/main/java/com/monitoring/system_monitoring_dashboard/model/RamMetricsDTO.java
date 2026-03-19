package com.monitoring.system_monitoring_dashboard.model;

/**
 * Data Transfer Object representing RAM metrics.
 * Contains information about total, available, used memory and usage percentage.
 */
public class RamMetricsDTO {
    /** Total RAM in megabytes. */
    private long totalMB;
    /** Available RAM in megabytes. */
    private long availableMB;
    /** Used RAM in megabytes. */
    private long usedMB;
    /** RAM usage percentage. */
    private double usagePercent;

    // Getters and setters
    public long getTotalMB() { return totalMB; }
    public void setTotalMB(long totalMB) { this.totalMB = totalMB; }

    public long getAvailableMB() { return availableMB; }
    public void setAvailableMB(long availableMB) { this.availableMB = availableMB; }

    public long getUsedMB() { return usedMB; }
    public void setUsedMB(long usedMB) { this.usedMB = usedMB; }

    public double getUsagePercent() { return usagePercent; }
    public void setUsagePercent(double usagePercent) { this.usagePercent = usagePercent; }
}

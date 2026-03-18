package com.monitoring.system_monitoring_dashboard.model;

public class RamMetricsDTO {
    private long totalMB;
    private long availableMB;
    private long usedMB;
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

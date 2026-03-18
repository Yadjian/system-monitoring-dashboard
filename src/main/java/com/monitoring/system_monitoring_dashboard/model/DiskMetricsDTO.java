package com.monitoring.system_monitoring_dashboard.model;

public class DiskMetricsDTO {
    private String name;
    private long totalMB;
    private long freeMB;
    private long usedMB;
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
}


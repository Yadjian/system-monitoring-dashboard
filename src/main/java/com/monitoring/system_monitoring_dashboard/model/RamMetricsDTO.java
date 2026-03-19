package com.monitoring.system_monitoring_dashboard.model;

import java.util.List;

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
    /** List of RAM manufacturers, one per slot. */
    private List<String> manufacturers;
    /** List of RAM part numbers (model/reference), one per slot. */
    private List<String> partNumbers;
    /** List of RAM speeds in MHz, one per slot. */
    private List<Long> speedsMHz;

    // Getters and setters
    public long getTotalMB() { return totalMB; }
    public void setTotalMB(long totalMB) { this.totalMB = totalMB; }

    public long getAvailableMB() { return availableMB; }
    public void setAvailableMB(long availableMB) { this.availableMB = availableMB; }

    public long getUsedMB() { return usedMB; }
    public void setUsedMB(long usedMB) { this.usedMB = usedMB; }

    public double getUsagePercent() { return usagePercent; }
    public void setUsagePercent(double usagePercent) { this.usagePercent = usagePercent; }

    public List<String> getManufacturers() { return manufacturers; }
    public void setManufacturers(List<String> manufacturers) { this.manufacturers = manufacturers; }

    public List<String> getPartNumbers() { return partNumbers; }
    public void setPartNumbers(List<String> partNumbers) { this.partNumbers = partNumbers; }

    public List<Long> getSpeedsMHz() { return speedsMHz; }
    public void setSpeedsMHz(List<Long> speedsMHz) { this.speedsMHz = speedsMHz; }
}

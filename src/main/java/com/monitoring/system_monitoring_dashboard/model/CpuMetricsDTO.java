package com.monitoring.system_monitoring_dashboard.model;

/**
 * Data Transfer Object representing CPU metrics.
 * Contains information about CPU name, usage, temperature, core counts, and frequencies.
 */
public class CpuMetricsDTO {
    /** CPU name or model. */
    private String name;
    /** Current CPU usage percentage. */
    private double usagePercent;
    /** Current CPU temperature. */
    private double temperature;
    /** Maximum recorded CPU temperature. */
    private double temperatureMax;
    /** Minimum recorded CPU temperature. */
    private double temperatureMin;
    /** Number of physical CPU cores. */
    private int physicalCores;
    /** Number of logical CPU cores. */
    private int logicalCores;
    /** Maximum CPU frequency. */
    private long maxFreq;
    /** Array of current frequencies for each core, in GHz. */
    private double[] currentFreqGHz;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getUsagePercent() { return usagePercent; }
    public void setUsagePercent(double usagePercent) { this.usagePercent = usagePercent; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public int getPhysicalCores() { return physicalCores; }
    public void setPhysicalCores(int physicalCores) { this.physicalCores = physicalCores; }

    public int getLogicalCores() { return logicalCores; }
    public void setLogicalCores(int logicalCores) { this.logicalCores = logicalCores; }

    public long getMaxFreq() { return maxFreq; }
    public void setMaxFreq(long maxFreq) { this.maxFreq = maxFreq; }

    public double[] getCurrentFreqGHz() { return currentFreqGHz; }
    public void setCurrentFreqGHz(double[] currentFreqGHz) { this.currentFreqGHz = currentFreqGHz; }

    public double getTemperatureMax() { return temperatureMax; }
    public void setTemperatureMax(double temperatureMax) { this.temperatureMax = temperatureMax; }

    public double getTemperatureMin() { return temperatureMin; }
    public void setTemperatureMin(double temperatureMin) { this.temperatureMin = temperatureMin; }

    private boolean critical;
    public boolean isCritical() { return critical; }
    public void setCritical(boolean critical) { this.critical = critical; }
}

package com.monitoring.system_monitoring_dashboard.model;

public class CpuMetricsDTO {
    private String name;
    private double usagePercent;
    private double temperature;
    private double temperatureMax;
    private double temperatureMin;
    private int physicalCores;
    private int logicalCores;
    private long maxFreq;
    private long[] currentFreq;

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

    public long[] getCurrentFreq() { return currentFreq; }
    public void setCurrentFreq(long[] currentFreq) { this.currentFreq = currentFreq; }

    public double getTemperatureMax() { return temperatureMax; }
    public void setTemperatureMax(double temperatureMax) { this.temperatureMax = temperatureMax; }

    public double getTemperatureMin() { return temperatureMin; }
    public void setTemperatureMin(double temperatureMin) { this.temperatureMin = temperatureMin; }
}

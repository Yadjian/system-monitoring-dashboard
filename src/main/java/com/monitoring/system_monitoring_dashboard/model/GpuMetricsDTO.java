package com.monitoring.system_monitoring_dashboard.model;

public class GpuMetricsDTO {
    private String name;
    private String vendor;
    private long vramTotalMB;
    private long vramUsedMB;
    private double temperature;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getVendor() { return vendor; }
    public void setVendor(String vendor) { this.vendor = vendor; }

    public long getVramTotalMB() { return vramTotalMB; }
    public void setVramTotalMB(long vramTotalMB) { this.vramTotalMB = vramTotalMB; }

    public long getVramUsedMB() { return vramUsedMB; }
    public void setVramUsedMB(long vramUsedMB) { this.vramUsedMB = vramUsedMB; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
}

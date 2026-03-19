package com.monitoring.system_monitoring_dashboard.model;

/**
 * Data Transfer Object representing GPU metrics.
 * Contains information about GPU name, vendor, VRAM, and temperature.
 */
public class GpuMetricsDTO {
    /** GPU name or model. */
    private String name;
    /** GPU vendor or manufacturer. */
    private String vendor;
    /** Total VRAM in megabytes. */
    private long vramTotalMB;
    /** Used VRAM in megabytes. */
    private long vramUsedMB;
    /** Current GPU temperature. */
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

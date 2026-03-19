package com.monitoring.system_monitoring_dashboard.model;

public class MemoryMetricsDTO {
    private String bankLabel;
    private long capacityMB;
    private long clockSpeedMHz;
    private String manufacturer;
    private String partNumber;
    private String serialNumber;

    public String getBankLabel() { return bankLabel; }
    public void setBankLabel(String bankLabel) { this.bankLabel = bankLabel; }

    public long getCapacityMB() { return capacityMB; }
    public void setCapacityMB(long capacityMB) { this.capacityMB = capacityMB; }

    public long getClockSpeedMHz() { return clockSpeedMHz; }
    public void setClockSpeedMHz(long clockSpeedMHz) { this.clockSpeedMHz = clockSpeedMHz; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public String getPartNumber() { return partNumber; }
    public void setPartNumber(String partNumber) { this.partNumber = partNumber; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
}

package com.monitoring.system_monitoring_dashboard.model;

public class NetworkMetricsDTO {
    private String interfaceName;
    private String ipAddress;
    private String macAddress;
    private long bytesSent;
    private long bytesReceived;
    private long packetsSent;
    private long packetsReceived;

    // Getters and setters
    public String getInterfaceName() { return interfaceName; }
    public void setInterfaceName(String interfaceName) { this.interfaceName = interfaceName; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getMacAddress() { return macAddress; }
    public void setMacAddress(String macAddress) { this.macAddress = macAddress; }

    public long getBytesSent() { return bytesSent; }
    public void setBytesSent(long bytesSent) { this.bytesSent = bytesSent; }

    public long getBytesReceived() { return bytesReceived; }
    public void setBytesReceived(long bytesReceived) { this.bytesReceived = bytesReceived; }

    public long getPacketsSent() { return packetsSent; }
    public void setPacketsSent(long packetsSent) { this.packetsSent = packetsSent; }

    public long getPacketsReceived() { return packetsReceived; }
    public void setPacketsReceived(long packetsReceived) { this.packetsReceived = packetsReceived; }
}

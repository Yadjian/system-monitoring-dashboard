package com.monitoring.system_monitoring_dashboard.model;

/**
 * Data Transfer Object representing network interface metrics.
 * Contains information about interface name, IP address, MAC address, bytes and packets sent/received.
 */
public class NetworkMetricsDTO {
    /** Network interface name. */
    private String interfaceName;
    /** IPv4 address of the interface. */
    private String ipAddress;
    /** MAC address of the interface. */
    private String macAddress;
    /** Number of bytes sent. */
    private long bytesSent;
    /** Number of bytes received. */
    private long bytesReceived;
    /** Number of packets sent. */
    private long packetsSent;
    /** Number of packets received. */
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

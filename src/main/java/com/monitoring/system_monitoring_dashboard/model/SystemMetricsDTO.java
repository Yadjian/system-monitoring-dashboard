package com.monitoring.system_monitoring_dashboard.model;

import java.util.List;

/**
 * Data Transfer Object representing global system metrics.
 * Contains CPU, RAM, disk, network, and GPU information to be sent as JSON in the API response.
 */
public class SystemMetricsDTO {
    /** CPU metrics. */
    private CpuMetricsDTO cpu;
    /** RAM metrics. */
    private RamMetricsDTO ram;
    /** List of disk metrics. */
    private List<DiskMetricsDTO> disks;
    /** List of network interface metrics. */
    private List<NetworkMetricsDTO> networks;
    /** GPU metrics. */
    private GpuMetricsDTO gpu;

    // Getters and setters
    public CpuMetricsDTO getCpu() { return cpu; }
    public void setCpu(CpuMetricsDTO cpu) { this.cpu = cpu; }

    public RamMetricsDTO getRam() { return ram; }
    public void setRam(RamMetricsDTO ram) { this.ram = ram; }

    public List<DiskMetricsDTO> getDisks() { return disks; }
    public void setDisks(List<DiskMetricsDTO> disks) { this.disks = disks; }

    public List<NetworkMetricsDTO> getNetworks() { return networks; }
    public void setNetworks(List<NetworkMetricsDTO> networks) { this.networks = networks; }

    public GpuMetricsDTO getGpu() { return gpu; }
    public void setGpu(GpuMetricsDTO gpu) { this.gpu = gpu; }
}

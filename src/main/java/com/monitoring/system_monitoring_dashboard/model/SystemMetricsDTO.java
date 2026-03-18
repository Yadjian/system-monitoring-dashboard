package com.monitoring.system_monitoring_dashboard.model;

import java.util.List;

/**
 * Data Transfer Object (DTO) for system metrics.
 * Contains CPU and RAM information to be sent as JSON in the API response.
 */
public class SystemMetricsDTO {
    private CpuMetricsDTO cpu;
    private RamMetricsDTO ram;
    private List<DiskMetricsDTO> disks;
    private List<NetworkMetricsDTO> networks;
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

package com.monitoring.system_monitoring_dashboard.model;

import java.util.List;

/**
 * Data Transfer Object representing global system metrics.
 * Contains CPU, RAM, disk, network, and GPU information to be sent as JSON in the API response.
 */
public class AllMetricsDTO {
    private String pcType;
    private PcInfoDTO pcInfo;
    private BiosInfoDTO biosInfo;
    private OsInfoDTO osInfo;
    private CpuMetricsDTO cpu;
    private RamMetricsDTO ram;
    private List<DiskMetricsDTO> disks;
    private GpuMetricsDTO gpu;
    private List<MemorySlotDTO> memorySlots;
    public String getPcType() { return pcType; }
    public void setPcType(String pcType) { this.pcType = pcType; }

    public PcInfoDTO getPcInfo() { return pcInfo; }
    public void setPcInfo(PcInfoDTO pcInfo) { this.pcInfo = pcInfo; }

    public BiosInfoDTO getBiosInfo() { return biosInfo; }
    public void setBiosInfo(BiosInfoDTO biosInfo) { this.biosInfo = biosInfo; }

    public OsInfoDTO getOsInfo() { return osInfo; }
    public void setOsInfo(OsInfoDTO osInfo) { this.osInfo = osInfo; }

    public CpuMetricsDTO getCpu() { return cpu; }
    public void setCpu(CpuMetricsDTO cpu) { this.cpu = cpu; }

    public RamMetricsDTO getRam() { return ram; }
    public void setRam(RamMetricsDTO ram) { this.ram = ram; }

    public List<DiskMetricsDTO> getDisks() { return disks; }
    public void setDisks(List<DiskMetricsDTO> disks) { this.disks = disks; }

    public GpuMetricsDTO getGpu() { return gpu; }
    public void setGpu(GpuMetricsDTO gpu) { this.gpu = gpu; }

    public List<MemorySlotDTO> getMemorySlots() { return memorySlots; }
    public void setMemorySlots(List<MemorySlotDTO> memorySlots) { this.memorySlots = memorySlots; }
}

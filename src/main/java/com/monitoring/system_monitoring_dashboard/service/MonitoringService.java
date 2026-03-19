package com.monitoring.system_monitoring_dashboard.service;

import com.monitoring.system_monitoring_dashboard.model.*;
import oshi.SystemInfo;
import oshi.hardware.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service responsible for retrieving system metrics using the OSHI library.
 * Provides methods to get metrics for CPU, RAM, disks, network interfaces, and GPU.
 */
@Service
public class MonitoringService {

    /** Singleton instance of SystemInfo for hardware access. */
    private final SystemInfo systemInfo = new SystemInfo();

    /**
     * Retrieves current CPU metrics.
     * @return CpuMetricsDTO containing CPU information and usage.
     */
    public CpuMetricsDTO getCpuMetrics() {
        var hardware = systemInfo.getHardware();
        CentralProcessor cpu = hardware.getProcessor();
        Sensors sensors = hardware.getSensors();
        long[] oldTicks = cpu.getSystemCpuLoadTicks();
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        double cpuLoad = cpu.getSystemCpuLoadBetweenTicks(oldTicks) * 100;
        CpuMetricsDTO cpuDto = new CpuMetricsDTO();
        cpuDto.setName(cpu.getProcessorIdentifier().getName());
        cpuDto.setUsagePercent(cpuLoad);
        cpuDto.setTemperature(sensors.getCpuTemperature());
        cpuDto.setPhysicalCores(cpu.getPhysicalProcessorCount());
        cpuDto.setLogicalCores(cpu.getLogicalProcessorCount());
        cpuDto.setMaxFreq(cpu.getMaxFreq());
        cpuDto.setCurrentFreq(cpu.getCurrentFreq());
        return cpuDto;
    }

    /**
     * Retrieves current RAM metrics.
     * @return RamMetricsDTO containing RAM information and usage.
     */
    public RamMetricsDTO getRamMetrics() {
        var hardware = systemInfo.getHardware();
        GlobalMemory memory = hardware.getMemory();
        RamMetricsDTO ramDto = new RamMetricsDTO();
        ramDto.setTotalMB(memory.getTotal() / (1024 * 1024));
        ramDto.setAvailableMB(memory.getAvailable() / (1024 * 1024));
        long usedMB = ramDto.getTotalMB() - ramDto.getAvailableMB();
        ramDto.setUsedMB(usedMB);
        ramDto.setUsagePercent((ramDto.getTotalMB() > 0) ? (usedMB * 100.0 / ramDto.getTotalMB()) : 0);
        List<String> manufacturers = new ArrayList<>();
        List<String> partNumbers = new ArrayList<>();
        List<Long> speedsMHz = new ArrayList<>();
        for (PhysicalMemory module : memory.getPhysicalMemory()) {
            manufacturers.add(module.getManufacturer());
            partNumbers.add(module.getBankLabel());
            speedsMHz.add(module.getClockSpeed());
        }
        ramDto.setManufacturers(manufacturers);
        ramDto.setPartNumbers(partNumbers);
        ramDto.setSpeedsMHz(speedsMHz);

        return ramDto;
    }

    /**
     * Retrieves current disk metrics for all disk drives.
     * @return List of DiskMetricsDTO containing disk information and usage.
     */
    public List<DiskMetricsDTO> getDiskMetrics() {
        var hardware = systemInfo.getHardware();
        List<DiskMetricsDTO> diskDtos = new ArrayList<>();
        for (HWDiskStore disk : hardware.getDiskStores()) {
            DiskMetricsDTO diskDto = new DiskMetricsDTO();
            diskDto.setName(disk.getName());
            diskDto.setTotalMB(disk.getSize() / (1024 * 1024));
            diskDto.setFreeMB(0);
            diskDto.setUsedMB(diskDto.getTotalMB() - diskDto.getFreeMB());
            diskDto.setUsagePercent((diskDto.getTotalMB() > 0) ? (diskDto.getUsedMB() * 100.0 / diskDto.getTotalMB()) : 0);
            diskDtos.add(diskDto);
        }
        return diskDtos;
    }

    /**
     * Retrieves current network metrics for all network interfaces.
     * @return List of NetworkMetricsDTO containing network information and usage.
     */
    public List<NetworkMetricsDTO> getNetworkMetrics() {
        var hardware = systemInfo.getHardware();
        List<NetworkMetricsDTO> networkDtos = new ArrayList<>();
        for (NetworkIF net : hardware.getNetworkIFs()) {
            NetworkMetricsDTO netDto = new NetworkMetricsDTO();
            netDto.setInterfaceName(net.getName());
            netDto.setIpAddress(net.getIPv4addr().length > 0 ? net.getIPv4addr()[0] : "");
            netDto.setMacAddress(net.getMacaddr());
            netDto.setBytesSent(net.getBytesSent());
            netDto.setBytesReceived(net.getBytesRecv());
            netDto.setPacketsSent(net.getPacketsSent());
            netDto.setPacketsReceived(net.getPacketsRecv());
            networkDtos.add(netDto);
        }
        return networkDtos;
    }

    /**
     * Retrieves current GPU metrics.
     * @return GpuMetricsDTO containing GPU information.
     */
    public GpuMetricsDTO getGpuMetrics() {
        var hardware = systemInfo.getHardware();
        List<GraphicsCard> gpus = hardware.getGraphicsCards();
        GpuMetricsDTO gpuDto = new GpuMetricsDTO();
        if (!gpus.isEmpty()) {
            GraphicsCard gpu = gpus.get(0);
            gpuDto.setName(gpu.getName());
            gpuDto.setVendor(gpu.getVendor());
            gpuDto.setVramTotalMB(gpu.getVRam() / (1024 * 1024));
            gpuDto.setVramUsedMB(0);
            gpuDto.setTemperature(0.0);
        }
        return gpuDto;
    }
    
    /**
     * Retrieves all system metrics (CPU, RAM, disks, network, GPU).
     * @return SystemMetricsDTO containing all system information.
     */
    public SystemMetricsDTO getSystemMetrics() {
        SystemMetricsDTO dto = new SystemMetricsDTO();
        dto.setCpu(getCpuMetrics());
        dto.setRam(getRamMetrics());
        dto.setDisks(getDiskMetrics());
        dto.setNetworks(getNetworkMetrics());
        dto.setGpu(getGpuMetrics());
        return dto;
    }
}

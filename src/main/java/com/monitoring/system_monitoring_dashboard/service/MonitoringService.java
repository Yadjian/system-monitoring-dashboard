package com.monitoring.system_monitoring_dashboard.service;

import com.monitoring.system_monitoring_dashboard.model.SystemMetricsDTO;
import com.monitoring.system_monitoring_dashboard.model.CpuMetricsDTO;
import com.monitoring.system_monitoring_dashboard.model.RamMetricsDTO;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.Sensors;
import org.springframework.stereotype.Service;
import com.monitoring.system_monitoring_dashboard.model.DiskMetricsDTO;
import com.monitoring.system_monitoring_dashboard.model.NetworkMetricsDTO;
import com.monitoring.system_monitoring_dashboard.model.GpuMetricsDTO;
import oshi.hardware.HWDiskStore;
import oshi.hardware.NetworkIF;
import oshi.hardware.GraphicsCard;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for retrieving system metrics using OSHI.
 */
@Service
public class MonitoringService {

    /**
     * Retrieves current system metrics (CPU and RAM) using OSHI.
     * Measures CPU usage over a 1-second interval.
     *
     * @return SystemMetricsDTO containing CPU name, usage, and RAM info
     */
    public SystemMetricsDTO getSystemMetrics() {
        SystemInfo systemInfo = new SystemInfo();
        var hardware = systemInfo.getHardware();

        // CPU
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

        // RAM
        GlobalMemory memory = hardware.getMemory();
        RamMetricsDTO ramDto = new RamMetricsDTO();
        ramDto.setTotalMB(memory.getTotal() / (1024 * 1024));
        ramDto.setAvailableMB(memory.getAvailable() / (1024 * 1024));
        long usedMB = ramDto.getTotalMB() - ramDto.getAvailableMB();
        ramDto.setUsedMB(usedMB);
        ramDto.setUsagePercent((ramDto.getTotalMB() > 0) ? (usedMB * 100.0 / ramDto.getTotalMB()) : 0);

        // Disks
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

        // Network
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

        // GPU
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

        // Main DTO
        SystemMetricsDTO dto = new SystemMetricsDTO();
        dto.setCpu(cpuDto);
        dto.setRam(ramDto);
        dto.setDisks(diskDtos);
        dto.setNetworks(networkDtos);
        dto.setGpu(gpuDto);

        return dto;
    }
}

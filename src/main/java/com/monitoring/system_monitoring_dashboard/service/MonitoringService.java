package com.monitoring.system_monitoring_dashboard.service;

import com.monitoring.system_monitoring_dashboard.model.SystemMetricsDTO;
import com.monitoring.system_monitoring_dashboard.model.CpuMetricsDTO;
import com.monitoring.system_monitoring_dashboard.model.RamMetricsDTO;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.Sensors;

import org.springframework.stereotype.Service;

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
        CentralProcessor cpu = systemInfo.getHardware().getProcessor();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        Sensors sensors = systemInfo.getHardware().getSensors();

        // Measure CPU usage over 1 second interval
        long[] oldTicks = cpu.getSystemCpuLoadTicks();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        double cpuLoad = cpu.getSystemCpuLoadBetweenTicks(oldTicks) * 100;

        // Create and fill the DTO CPU
        CpuMetricsDTO cpuDto = new CpuMetricsDTO();
        cpuDto.setName(cpu.getProcessorIdentifier().getName());
        cpuDto.setUsagePercent(cpuLoad);
        cpuDto.setTemperature(sensors.getCpuTemperature());
        cpuDto.setPhysicalCores(cpu.getPhysicalProcessorCount());
        cpuDto.setLogicalCores(cpu.getLogicalProcessorCount());
        cpuDto.setMaxFreq(cpu.getMaxFreq());
        cpuDto.setCurrentFreq(cpu.getCurrentFreq());

        // Create and fill the DTO RAM
        RamMetricsDTO ramDto = new RamMetricsDTO();
        ramDto.setTotalMB(memory.getTotal() / (1024 * 1024));
        ramDto.setAvailableMB(memory.getAvailable() / (1024 * 1024));

        // Grouping in the main DTO
        SystemMetricsDTO dto = new SystemMetricsDTO();
        dto.setCpu(cpuDto);
        dto.setRam(ramDto);

        return dto;
    }
}

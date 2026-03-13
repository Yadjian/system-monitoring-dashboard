package com.monitoring.system_monitoring_dashboard.service;

import com.monitoring.system_monitoring_dashboard.model.SystemMetricsDTO;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
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

        // Measure CPU usage over 1 second interval
        long[] oldTicks = cpu.getSystemCpuLoadTicks();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        double cpuLoad = cpu.getSystemCpuLoadBetweenTicks(oldTicks) * 100;

        // Create and fill the DTO
        SystemMetricsDTO dto = new SystemMetricsDTO();
        dto.setCpuName(cpu.getProcessorIdentifier().getName());
        dto.setCpuUsagePercent(String.format("%.2f", cpuLoad));
        dto.setRamTotalMB(memory.getTotal() / (1024 * 1024));
        dto.setRamAvailableMB(memory.getAvailable() / (1024 * 1024));

        return dto;
    }
}

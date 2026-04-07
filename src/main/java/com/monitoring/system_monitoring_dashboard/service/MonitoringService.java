package com.monitoring.system_monitoring_dashboard.service;

import com.monitoring.system_monitoring_dashboard.model.*;
import oshi.SystemInfo;
import oshi.hardware.*;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Complete service for the system dashboard, strictly based on the validated list.
 */
@Service
public class MonitoringService {
    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);
    
    /** Singleton instance of SystemInfo for hardware access. */
    private final SystemInfo systemInfo = new SystemInfo();

    /** Scheduled executor for async CPU load update */
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    /** Cached CPU metrics */
    private volatile CpuMetricsDTO cachedCpuMetrics;

    /** Init block to start async CPU monitoring */
    {
        cachedCpuMetrics = new CpuMetricsDTO(); // Initialize with empty DTO
        scheduler.scheduleAtFixedRate(this::updateCpuMetrics, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * Aggregates all dynamic and static metrics into a single object.
     * @return AllMetricsDTO containing all dashboard information.
     */
    public AllMetricsDTO getAllMetrics() {
        AllMetricsDTO all = new AllMetricsDTO();
        all.setPcType(getPcType());
        all.setPcInfo(getPcInfo());
        all.setBiosInfo(getBiosInfo());
        all.setOsInfo(getOsInfo());
        all.setCpu(getCpuMetrics());
        all.setRam(getRamMetrics());
        all.setMemorySlots(getMemorySlots());
        all.setDisks(getDiskMetrics());
        return all;
    }

    /**
     * PC type (Laptop or Desktop)
     */
    public String getPcType() {
        var hardware = systemInfo.getHardware();
        // Simple heuristic: battery presence = laptop
        return hardware.getPowerSources().isEmpty() ? "PC Fixe" : "PC Portable";
    }

    /**
     * PC info (manufacturer, model, motherboard)
     */
    public PcInfoDTO getPcInfo() {
        var hardware = systemInfo.getHardware();
        PcInfoDTO dto = new PcInfoDTO();
        dto.setManufacturer(hardware.getComputerSystem().getManufacturer());
        dto.setModel(hardware.getComputerSystem().getModel());
        dto.setMotherboard(hardware.getComputerSystem().getBaseboard().getModel());

        return dto;
    }

    /**
     * BIOS info (vendor, version, release date)
     */
    public BiosInfoDTO getBiosInfo() {
        var hardware = systemInfo.getHardware();
        var firmware = hardware.getComputerSystem().getFirmware();
        BiosInfoDTO dto = new BiosInfoDTO();
        dto.setVendor(firmware.getManufacturer());
        dto.setVersion(firmware.getVersion());
        dto.setReleaseDate(firmware.getReleaseDate());

        return dto;
    }

    /**
     * OS info (edition, version, boot time, uptime)
     */
    public OsInfoDTO getOsInfo() {
        var os = systemInfo.getOperatingSystem();
        OsInfoDTO dto = new OsInfoDTO();
        dto.setEdition(os.getFamily());
        dto.setVersion(os.getVersionInfo().getVersion());
        dto.setInstallDate(os.getSystemBootTime());
        dto.setUptime(os.getSystemUptime());

        return dto;
    }

    /** Store previous ticks for delta calculation */
    private long[] prevTicks = null;

    /** Updates the cached CPU metrics asynchronously */
    private void updateCpuMetrics() {
        try {
            var hardware = systemInfo.getHardware();
            CentralProcessor cpu = hardware.getProcessor();
            Sensors sensors = hardware.getSensors();
            
            // Calculate CPU load between two tick measurements
            long[] currTicks = cpu.getSystemCpuLoadTicks();
            double cpuLoad = 0;
            
            if (prevTicks != null) {
                cpuLoad = cpu.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
            }
            prevTicks = currTicks;
            
            CpuMetricsDTO cpuDto = new CpuMetricsDTO();
            cpuDto.setName(cpu.getProcessorIdentifier().getName());
            cpuDto.setUsagePercent(Math.max(0, cpuLoad)); // Ensure >= 0
            cpuDto.setTemperature(sensors.getCpuTemperature());
            cpuDto.setPhysicalCores(cpu.getPhysicalProcessorCount());
            cpuDto.setLogicalCores(cpu.getLogicalProcessorCount());
            cpuDto.setMaxFreq(cpu.getMaxFreq());
            cpuDto.setCurrentFreq(cpu.getCurrentFreq());
            cachedCpuMetrics = cpuDto;
            logger.debug("CPU updated: {}%", cpuLoad);
        } catch (Exception e) {
            logger.error("Error updating CPU metrics", e);
        }
    }

    /**
     * CPU: name, cores/threads, usage, current frequency
     */
    public CpuMetricsDTO getCpuMetrics() {
        return cachedCpuMetrics != null ? cachedCpuMetrics : new CpuMetricsDTO();
    }

    /**
     * RAM: total size, type, usage (%)
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
        return ramDto;
    }

    /**
     * Storage: model, type, capacity
     */
    public List<DiskMetricsDTO> getDiskMetrics() {
        var hardware = systemInfo.getHardware();
        List<DiskMetricsDTO> diskDtos = new ArrayList<>();
        for (HWDiskStore disk : hardware.getDiskStores()) {
            DiskMetricsDTO diskDto = new DiskMetricsDTO();
            diskDto.setName(disk.getModel());
            diskDto.setType(disk.getModel().toLowerCase().contains("ssd") ? "SSD" : "HDD");
            diskDto.setTotalMB(disk.getSize() / (1024 * 1024));

            diskDtos.add(diskDto);
        }
        return diskDtos;
    }


    /**
     * GPU: model, vendor, VRAM
     */
    public GpuMetricsDTO getGpuMetrics() {
        GpuMetricsDTO gpuDto = new GpuMetricsDTO();
        gpuDto.setName("No GPU detected");
        gpuDto.setVendor("N/A");
        gpuDto.setVramTotalMB(0);
        return gpuDto;
    }

    /**
     * Memory slot: manufacturer, part number, size/frequency
     */
    public List<MemorySlotDTO> getMemorySlots() {
        var hardware = systemInfo.getHardware();
        List<MemorySlotDTO> slots = new ArrayList<>();
        for (PhysicalMemory mem : hardware.getMemory().getPhysicalMemory()) {
            MemorySlotDTO slot = new MemorySlotDTO();
            slot.setManufacturer(mem.getManufacturer());
            slot.setPartNumber(mem.getBankLabel());
            slot.setClockSpeedMHz(mem.getClockSpeed() / 1_000_000);
            slots.add(slot);
        }
        return slots;
    }
}

export interface PcInfo {
  manufacturer: string;
  model: string;
  motherboard: string;
}

export interface BiosInfo {
  vendor: string;
  version: string;
  releaseDate: string;
}

export interface OsInfo {
  edition: string;
  version: string;
  installDate: number;
  uptime: number;
}

export interface CpuMetrics {
  name: string;
  usagePercent: number;
  temperature: number;
  physicalCores: number;
  logicalCores: number;
  maxFreq: number;
  currentFreq: number[];
}

export interface RamMetrics {
  totalMB: number;
  availableMB: number;
  usedMB: number;
  usagePercent: number;
}

export interface DiskMetrics {
  name: string;
  totalMB: number;
  type: string;
}

export interface MemorySlot {
  manufacturer: string;
  partNumber: string;
  capacityMB: number;
  clockSpeedMHz: number;
}

export interface AllMetrics {
  pcType: string;
  pcInfo: PcInfo;
  biosInfo: BiosInfo;
  osInfo: OsInfo;
  cpu: CpuMetrics;
  ram: RamMetrics;
  disks: DiskMetrics[];
  memorySlots: MemorySlot[];
}
import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule, DecimalPipe } from '@angular/common';
import { ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { WebSocketService } from '../../websocket.service';
import { Subscription } from 'rxjs';
import { AllMetrics } from '../../models/metrics';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, DecimalPipe],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class Dashboard implements OnInit, OnDestroy {
  metrics: AllMetrics | null = null;
  private subscription: Subscription | null = null;

  constructor(private webSocketService: WebSocketService, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    // Connect to WebSocket and subscribe to metrics
    this.webSocketService.connect();
    this.subscription = this.webSocketService.getMetrics().subscribe({
      next: data => {
        if (data) {
          this.metrics = data;
          this.cdr.markForCheck();
        }
      },
      error: err => console.error('Error while receiving metrics', err)
    });
  }

  ngOnDestroy() {
    // Disconnect and cleanup on component destroy
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
    this.webSocketService.disconnect();
  }

  clampPercent(value: number | undefined): number {
    if (typeof value !== 'number' || Number.isNaN(value)) {
      return 0;
    }
    return Math.min(100, Math.max(0, value));
  }

  formatFreqArray(freqArray: number[] | undefined): string {
    if (!Array.isArray(freqArray) || freqArray.length === 0) {
      return 'N/A';
    }

    return freqArray
      .map((hz: number) => (hz > 0 ? `${(hz / 1_000_000).toFixed(0)} MHz` : 'N/A'))
      .join(', ');
  }

  formatMhz(hz: number | undefined): string {
    if (typeof hz !== 'number' || hz <= 0) {
      return 'N/A';
    }
    return `${(hz / 1_000_000).toFixed(0)} MHz`;
  }

  formatTemperature(temp: number | undefined): string {
    if (typeof temp !== 'number' || temp <= 0) {
      return 'N/A';
    }
    return `${temp.toFixed(1)} degC`;
  }

  formatBytes(value: number | undefined): string {
    if (typeof value !== 'number' || value < 0) {
      return 'N/A';
    }
    const units = ['B', 'KB', 'MB', 'GB', 'TB'];
    let scaled = value;
    let unitIndex = 0;
    while (scaled >= 1024 && unitIndex < units.length - 1) {
      scaled /= 1024;
      unitIndex += 1;
    }
    return `${scaled.toFixed(unitIndex === 0 ? 0 : 1)} ${units[unitIndex]}`;
  }

  formatUptime(seconds: number | undefined): string {
    if (typeof seconds !== 'number' || seconds < 0) {
      return 'N/A';
    }

    const days = Math.floor(seconds / 86400);
    const hours = Math.floor((seconds % 86400) / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);

    if (days > 0) {
      return `${days}d ${hours}h ${minutes}m`;
    }
    return `${hours}h ${minutes}m`;
  }

  formatEpochDate(epochSeconds: number | undefined): string {
    if (typeof epochSeconds !== 'number' || epochSeconds <= 0) {
      return 'N/A';
    }
    return new Date(epochSeconds * 1000).toLocaleString();
  }
}

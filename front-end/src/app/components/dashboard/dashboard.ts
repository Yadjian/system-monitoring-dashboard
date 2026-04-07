import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule, DecimalPipe } from '@angular/common';
import { ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { WebSocketService } from '../../websocket.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, DecimalPipe],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class Dashboard implements OnInit, OnDestroy {
  metrics: any;
  private subscription: Subscription | null = null;

  constructor(private webSocketService: WebSocketService, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    // Connect to WebSocket and subscribe to metrics
    this.webSocketService.connect();
    this.subscription = this.webSocketService.getMetrics().subscribe({
      next: data => {
        if (data) {
          console.log('Metrics reçues:', data);
          this.metrics = data;
          this.cdr.markForCheck();
        }
      },
      error: err => console.error('Erreur lors de la réception des métriques', err)
    });
  }

  ngOnDestroy() {
    // Disconnect and cleanup on component destroy
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
    this.webSocketService.disconnect();
  }

  /**
   * Format an array of CPU frequencies (Hz) as a comma-separated string in MHz, rounded to 2 decimals.
   * Returns '-' if input is not a valid array.
   */
  formatFreqArray(freqArray: any): string {
    if (!Array.isArray(freqArray) || freqArray.length === 0) return '-';
    return freqArray
      .map((hz: number) => hz ? (hz / 1_000_000).toFixed(2) : '-')
      .join(', ');
  }
}

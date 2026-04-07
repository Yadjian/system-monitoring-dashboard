import { Component, OnInit } from '@angular/core';
import { CommonModule, DecimalPipe } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';
import { Monitoring } from '../../monitoring';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, DecimalPipe],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  metrics: any;

  constructor(private monitoring: Monitoring, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.monitoring.getMetrics().subscribe({
      next: data => {
        this.metrics = data;
        this.cdr.detectChanges();
      },
      error: err => console.error('Erreur lors du chargement des métriques', err)
    });
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

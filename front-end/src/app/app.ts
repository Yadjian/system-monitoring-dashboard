import { Component, signal, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Monitoring } from './monitoring';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [CommonModule, RouterModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit, OnDestroy {
  protected readonly title = signal('monitoring-dashboard');
  metrics: any = null;
  intervalId: any;

  constructor(private monitoringService: Monitoring) {}

  ngOnInit() {
    this.fetchMetrics();
    this.intervalId = setInterval(() => this.fetchMetrics(), 2000);
  }

  fetchMetrics() {
    this.monitoringService.getMetrics().subscribe(data => {
      this.metrics = data;
    });
  }

  ngOnDestroy() {
    clearInterval(this.intervalId);
  }
}

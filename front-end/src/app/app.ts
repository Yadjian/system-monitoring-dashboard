import { Component, signal, OnInit, OnDestroy } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Monitoring } from './monitoring';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
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

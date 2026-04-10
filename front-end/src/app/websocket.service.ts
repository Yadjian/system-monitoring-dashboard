import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Client } from '@stomp/stompjs';
import { AllMetrics } from './models/metrics';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private client: Client | null = null;
  private metricsSubject = new BehaviorSubject<AllMetrics | null>(null);
  public metrics$: Observable<AllMetrics | null> = this.metricsSubject.asObservable();

  constructor() {}

  /**
   * Connect to the WebSocket server and subscribe to metrics topic
   */
  connect(): void {
    if (this.client && this.client.connected) {
      return;
    }

    this.client = new Client({
      brokerURL: 'ws://localhost:8080/ws-metrics',
      connectHeaders: {},
      debug: (msg: string) => console.log(msg),
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000
    });

    this.client.onConnect = () => {
      console.log('WebSocket connected successfully');

      // Subscribe to metrics topic
      this.client?.subscribe('/topic/metrics', (message) => {
        try {
          const metrics = JSON.parse(message.body) as AllMetrics;
          this.metricsSubject.next(metrics);
        } catch (e) {
          console.error('Error parsing metrics:', e);
        }
      });
    };

    this.client.onStompError = (frame) => {
      console.error('STOMP error:', frame.headers['message'], frame.body);
    };

    this.client.activate();
  }

  /**
   * Disconnect from WebSocket server
   */
  disconnect(): void {
    if (this.client && this.client.connected) {
      this.client.deactivate().then(() => {
        console.log('WebSocket disconnected');
      });
    }
  }

  /**
   * Get the metrics observable
   */
  getMetrics(): Observable<AllMetrics | null> {
    return this.metrics$;
  }
}

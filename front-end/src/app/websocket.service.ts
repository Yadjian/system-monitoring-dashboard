import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Client } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private client: Client | null = null;
  private metricsSubject = new BehaviorSubject<any>(null);
  public metrics$: Observable<any> = this.metricsSubject.asObservable();

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
          const metrics = JSON.parse(message.body);
          const clonedMetrics = JSON.parse(JSON.stringify(metrics));
          console.log('Message reçu:', clonedMetrics);
          this.metricsSubject.next(clonedMetrics);
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
  getMetrics(): Observable<any> {
    return this.metrics$;
  }
}

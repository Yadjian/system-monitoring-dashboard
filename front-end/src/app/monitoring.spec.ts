import { TestBed } from '@angular/core/testing';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Monitoring } from './monitoring';

@Injectable({
  providedIn: 'root'
})
export class MonitoringService {
  private apiUrl = 'http://localhost:8080/api/monitoring'; // adapte l’URL si besoin

  constructor(private http: HttpClient) { }

  getMetrics(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}

describe('Monitoring', () => {
  let service: Monitoring;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Monitoring);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule, DecimalPipe } from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [CommonModule, DecimalPipe],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home implements OnInit {
  metrics: any;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get('/api/monitoring').subscribe({
      next: data => this.metrics = data,
      error: err => console.error('Erreur lors du chargement des métriques', err)
    });
  }
}

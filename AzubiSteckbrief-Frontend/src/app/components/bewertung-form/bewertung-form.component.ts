import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-bewertung-form',
  templateUrl: './bewertung-form.component.html',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
})
export class BewertungFormComponent implements OnInit {
  azubiId!: number;
  referatId?: number;
  schulungId?: number;

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    const params = this.route.snapshot.queryParams;
    this.azubiId = +params['azubiId'];
    this.referatId = params['referatId'] ? +params['referatId'] : undefined;
    this.schulungId = params['schulungId'] ? +params['schulungId'] : undefined;

    console.log('Bewertung starten für:', {
      azubiId: this.azubiId,
      referatId: this.referatId,
      schulungId: this.schulungId,
    });
  }

  bewertungSpeichern() {
    const body: any = {
      azubi: { azubi_id: this.azubiId }
    };

    if (this.referatId) {
      body.referat = { referat_id: this.referatId };
    } else if (this.schulungId) {
      body.schulung = { schulung_id: this.schulungId };
    }

    console.log('Sende Payload:', body);

    this.http.post('/api/bewertungen', body).subscribe({
      next: (res) => console.log('Erfolg!', res),
      error: (err) => console.error('Fehler beim Speichern', err)
    });
  }

}

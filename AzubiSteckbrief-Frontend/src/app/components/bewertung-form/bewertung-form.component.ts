import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { VermittlungsstrukturComponent } from '../vermittlungsstruktur/vermittlungsstruktur.component';

@Component({
  selector: 'app-bewertung-form',
  templateUrl: './bewertung-form.component.html',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, VermittlungsstrukturComponent],
})
export class BewertungFormComponent implements OnInit {
  azubiId!: number;
  referatId?: number;
  schulungId?: number;

  ausgewaehltePunkte: Record<number, boolean> = {};
  ausgewaehlteTexte: string[] = [];

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

  onPunkteGeaendert(punkteIds: number[]) {
    this.ausgewaehltePunkte = {};
    punkteIds.forEach(id => this.ausgewaehltePunkte[id] = true);
    console.log('Ausgewählte Punkte:', this.ausgewaehltePunkte);
  }

  bewertungSpeichern() {
    const erledigtePunkte = Object.entries(this.ausgewaehltePunkte)
      .map(([unterpunktId, status]) => ({
        unterpunktId: +unterpunktId,
        status
      }));

    const payload: any = {
      azubiId: this.azubiId,
      erledigtePunkte
    };

    if (this.referatId) payload.referatId = this.referatId;
    if (this.schulungId) payload.schulungId = this.schulungId;

    console.log('Sende Payload JSON:', JSON.stringify(payload, null, 2));

    this.http.post('/api/bewertungen', payload).subscribe({
      next: (res) => console.log('Erfolgreich gespeichert!', res),
      error: (err) => console.error('Fehler beim Speichern:', err)
    });
  }
}

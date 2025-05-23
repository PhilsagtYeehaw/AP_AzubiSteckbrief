import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { VermittlungsstrukturComponent } from '../vermittlungsstruktur/vermittlungsstruktur.component';
import {NotenTabelleComponent} from '../noten-tabelle/noten-tabelle.component';
import {SozialverhaltenTabelleComponent} from '../sozialverhalten-tabelle/sozialverhalten-tabelle.component';

@Component({
  selector: 'app-bewertung-form',
  templateUrl: './bewertung-form.component.html',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, VermittlungsstrukturComponent, NotenTabelleComponent, SozialverhaltenTabelleComponent],
})
export class BewertungFormComponent implements OnInit {
  azubiId!: number;
  referatId?: number;
  schulungId?: number;
  bewertungId?: number; // Neue ID zum Speichern oder Aktualisieren

  ausgewaehltePunkte: Record<number, boolean> = {};
  ausgewaehlteTexte: string[] = [];

  notenAuswahl: { [id: number]: string } = {};

  sozialverhaltenNoten: { [id: number]: string } = {};
  sozialverhaltenAuswahl: { [id: number]: string } = {};

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient
  ) {}

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

    // 🛠️ Nur gültige Parameter an das Backend senden:
    const requestParams: any = { azubiId: this.azubiId };

    if (this.referatId) {
      requestParams.referatId = this.referatId;
    } else if (this.schulungId) {
      requestParams.schulungId = this.schulungId;
    } else {
      console.warn('Weder referatId noch schulungId vorhanden – kein Backend-Check möglich');
      return;
    }

    this.http.get(`/api/bewertungen/bestehende`, { params: requestParams }).subscribe({
      next: (id: any) => {
        if (id) {
          this.bewertungId = id;
          console.log('Vorhandene Bewertung wird überschrieben, ID:', this.bewertungId);

          // ✅ Noten nachladen
          this.http.get<any>(`/api/bewertungen/${id}/noten`).subscribe({
            next: (notenData) => {
              // Fachliche Noten
              if (notenData.inhaltNoten) {
                notenData.inhaltNoten.forEach((n: any) => {
                  this.notenAuswahl[n.leistungsbewertungInhaltId] = n.note;
                });
              }

              // Sozialverhalten Noten
              if (notenData.sozialverhaltenNoten) {
                notenData.sozialverhaltenNoten.forEach((n: any) => {
                  this.sozialverhaltenAuswahl[n.leistungsbewertungSozialverhaltenId] = n.note;
                });
              }

              console.log('Geladene Noten:', {
                fachlich: this.notenAuswahl,
                sozial: this.sozialverhaltenAuswahl
              });
            },
            error: (err) => console.error('Fehler beim Laden der Noten:', err)
          });
        }
      },
      error: (err) => {
        console.error('Fehler beim Laden bestehender Bewertung:', err);
      }
    });
  }

  onPunkteGeaendert(punkteIds: number[]) {
    this.ausgewaehltePunkte = {};
    punkteIds.forEach(id => this.ausgewaehltePunkte[id] = true);
    console.log('Ausgewählte Punkte:', this.ausgewaehltePunkte);
  }

  onPunktTexteGeaendert(texte: string[]) {
    this.ausgewaehlteTexte = texte;
  }

  onSozialverhaltenGeaendert(noten: { [id: number]: string }) {
    this.sozialverhaltenNoten = noten;
  }

  bewertungSpeichern() {
    const erledigtePunkte = Object.entries(this.ausgewaehltePunkte)
      .map(([unterpunktId, status]) => ({ unterpunktId: +unterpunktId, status }));

    const payload: any = {
      azubiId: this.azubiId,
      erledigtePunkte
    };

    const inhaltNoten = Object.entries(this.notenAuswahl)
      .filter(([_, note]) => !!note)
      .map(([id, note]) => ({
        leistungsbewertungInhaltId: +id,
        note
      }));
    payload.inhaltNoten = inhaltNoten;

    const sozialverhaltenNoten = Object.entries(this.sozialverhaltenAuswahl)
      .filter(([_, note]) => !!note)
      .map(([id, note]) => ({
        leistungsbewertungSozialverhaltenId: +id,
        note
      }));
    payload.sozialverhaltenNoten = sozialverhaltenNoten;


    if (this.referatId) payload.referatId = this.referatId;
    if (this.schulungId) payload.schulungId = this.schulungId;
    if (this.bewertungId) payload.bewertungId = this.bewertungId; // ID mitgeben zum Überschreiben

    console.log('Sende Payload JSON:', JSON.stringify(payload, null, 2));

    this.http.post('/api/bewertungen', payload).subscribe({
      next: (res) => {
        console.log('Erfolgreich gespeichert!', res);
        this.router.navigate([`/azubi/${this.azubiId}`]); // Zur Detailansicht zurück
      },
      error: (err) => console.error('Fehler beim Speichern:', err)
    });
  }
}

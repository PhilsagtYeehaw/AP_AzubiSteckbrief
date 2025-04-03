import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-vermittlungsstruktur',
  templateUrl: './vermittlungsstruktur.component.html',
  standalone: true,
  imports: [FormsModule, CommonModule],
})
export class VermittlungsstrukturComponent implements OnInit {
  @Input() azubiId!: number;
  @Output() punkteGeaendert = new EventEmitter<number[]>();
  @Output() punktTexteGeaendert = new EventEmitter<string[]>();

  vermittlungsstruktur: any[] = [];
  dropdownGeoeffnet = false;
  offeneBerufe: number[] = [];
  offeneVermittlungen: number[] = [];
  ausgewaehltePunkte: { [punktId: number]: boolean } = {};

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.ladeVermittlungsstruktur();
  }

  ladeVermittlungsstruktur() {
    this.http.get<any[]>(`/api/vermittlungsstruktur/${this.azubiId}`).subscribe({
      next: (struktur) => {
        this.vermittlungsstruktur = struktur;

        struktur.forEach((beruf: any) => {
          beruf.vermittlungen.forEach((vermittlung: any) => {
            vermittlung.unterpunkteDTO.forEach((punkt: any) => {
              this.ausgewaehltePunkte[punkt.unterpunktId] = punkt.status === true;
            });
          });
        });

        // 🔁 IDs der aktivierten Punkte ermitteln
        const selektierte = Object.entries(this.ausgewaehltePunkte)
          .filter(([_, val]) => val)
          .map(([id]) => +id);
        this.punkteGeaendert.emit(selektierte);

        // ✅ Texte zu den selektierten Punkten
        const selektierteTexte = struktur
          .flatMap((beruf: any) => beruf.vermittlungen)
          .flatMap((vermittlung: any) => vermittlung.unterpunkteDTO)
          .filter((p: any) => this.ausgewaehltePunkte[p.unterpunktId])
          .map((p: any) => p.unterpunkt);

        this.punktTexteGeaendert.emit(selektierteTexte);
      },
      error: (err) => {
        console.error('Fehler beim Laden der Vermittlungsstruktur', err);
      }
    });
  }

  toggleDropdown() {
    this.dropdownGeoeffnet = !this.dropdownGeoeffnet;
  }

  toggleBeruf(beruf: any) {
    const index = this.offeneBerufe.indexOf(beruf.ausbildungsberufsbildId);
    index > -1
      ? this.offeneBerufe.splice(index, 1)
      : this.offeneBerufe.push(beruf.ausbildungsberufsbildId);
  }

  toggleVermittlung(vermittlung: any) {
    const index = this.offeneVermittlungen.indexOf(vermittlung.vermittlungId);
    index > -1
      ? this.offeneVermittlungen.splice(index, 1)
      : this.offeneVermittlungen.push(vermittlung.vermittlungId);
  }

  checkboxGeaendert(punkt: any, event?: any) {
    this.ausgewaehltePunkte[punkt.unterpunktId] = event?.target?.checked ?? false;

    const selektierte = Object.entries(this.ausgewaehltePunkte)
      .filter(([_, val]) => val)
      .map(([id]) => +id);
    this.punkteGeaendert.emit(selektierte);

    const selektierteTexte = this.vermittlungsstruktur
      .flatMap((beruf: any) => beruf.vermittlungen)
      .flatMap((vermittlung: any) => vermittlung.unterpunkteDTO)
      .filter((p: any) => this.ausgewaehltePunkte[p.unterpunktId])
      .map((p: any) => p.unterpunkt);
    this.punktTexteGeaendert.emit(selektierteTexte);
  }
}

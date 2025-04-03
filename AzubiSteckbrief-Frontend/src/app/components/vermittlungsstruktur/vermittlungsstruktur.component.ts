import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  OnChanges,
  SimpleChanges
} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-vermittlungsstruktur',
  templateUrl: './vermittlungsstruktur.component.html',
  standalone: true,
  imports: [FormsModule, CommonModule],
})
export class VermittlungsstrukturComponent implements OnInit, OnChanges {
  @Input() azubiId!: number;
  @Input() bewertungId?: number;
  @Output() punkteGeaendert = new EventEmitter<number[]>();
  @Output() punktTexteGeaendert = new EventEmitter<string[]>();

  vermittlungsstruktur: any[] = [];
  dropdownGeoeffnet = false;
  offeneBerufe: number[] = [];
  offeneVermittlungen: number[] = [];
  ausgewaehltePunkte: { [punktId: number]: boolean } = {};

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    if (this.azubiId) {
      this.ladeVermittlungsstruktur();
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['bewertungId'] && !changes['bewertungId'].firstChange) {
      this.ladeVermittlungsstruktur();
    }
  }

  ladeVermittlungsstruktur() {
    const url = this.bewertungId
      ? `/api/vermittlungsstruktur/struktur/${this.bewertungId}`   // bestehende Bewertung
      : `/api/vermittlungsstruktur/naked/${this.azubiId}`;         // neue Bewertung → keine Punkte

    this.http.get<any[]>(url).subscribe({
      next: (struktur) => {
        this.vermittlungsstruktur = struktur;

        struktur.forEach((beruf: any) => {
          beruf.vermittlungen.forEach((vermittlung: any) => {
            vermittlung.unterpunkteDTO.forEach((punkt: any) => {
              this.ausgewaehltePunkte[punkt.unterpunktId] = punkt.status === true;
            });
          });
        });

        const selektierte = Object.entries(this.ausgewaehltePunkte)
          .filter(([_, val]) => val)
          .map(([id]) => +id);
        this.punkteGeaendert.emit(selektierte);

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

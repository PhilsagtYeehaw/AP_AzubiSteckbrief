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

        // Initialisiere Map mit Status aus unterpunkteDTO
        struktur.forEach((beruf: any) => {
          beruf.vermittlungen.forEach((vermittlung: any) => {
            vermittlung.unterpunkteDTO.forEach((punkt: any) => {
              this.ausgewaehltePunkte[punkt.unterpunktId] = punkt.status === true;
            });
          });
        });
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
    if (index > -1) {
      this.offeneBerufe.splice(index, 1);
    } else {
      this.offeneBerufe.push(beruf.ausbildungsberufsbildId);
    }
  }

  toggleVermittlung(vermittlung: any) {
    const index = this.offeneVermittlungen.indexOf(vermittlung.vermittlungId);
    if (index > -1) {
      this.offeneVermittlungen.splice(index, 1);
    } else {
      this.offeneVermittlungen.push(vermittlung.vermittlungId);
    }
  }

  checkboxGeaendert(punkt: any, event: Event) {
    const checked = (event.target as HTMLInputElement).checked;
    this.ausgewaehltePunkte[punkt.unterpunktId] = checked;

    const selektierte = Object.entries(this.ausgewaehltePunkte)
      .filter(([_, val]) => val)
      .map(([id]) => +id);

    this.punkteGeaendert.emit(selektierte);
  }
}

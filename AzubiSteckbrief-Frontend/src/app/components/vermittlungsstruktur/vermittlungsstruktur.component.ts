import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-vermittlungsstruktur',
  templateUrl: './vermittlungsstruktur.component.html',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule
  ],
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
    this.http.get<any[]>('/api/vermittlungsstruktur/${this.azubiId}').subscribe({
      next: (struktur) => {
        this.vermittlungsstruktur = struktur;
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
    const index = this.offeneBerufe.indexOf(beruf.id);
    if (index > -1) {
      this.offeneBerufe.splice(index, 1);
    } else {
      this.offeneBerufe.push(beruf.id);
    }
  }

  toggleVermittlung(vermittlung: any) {
    const index = this.offeneVermittlungen.indexOf(vermittlung.id);
    if (index > -1) {
      this.offeneVermittlungen.splice(index, 1);
    } else {
      this.offeneVermittlungen.push(vermittlung.id);
    }
  }

  checkboxGeaendert(punkt: any) {
    const ausgewaehlt = this.ausgewaehltePunkte[punkt.id];

    const selektierte = Object.entries(this.ausgewaehltePunkte)
      .filter(([_, val]) => val)
      .map(([id]) => +id);

    this.punkteGeaendert.emit(selektierte);
  }



}

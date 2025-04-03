import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-sozialverhalten-tabelle',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './sozialverhalten-tabelle.component.html'
})
export class SozialverhaltenTabelleComponent {
  @Input() noten: { [id: number]: string } = {};
  @Output() notenGeaendert = new EventEmitter<{ [id: number]: string }>();

  sozialverhaltenInhalte: any[] = [];
  optionen = ['Einwandfrei', 'Nicht Bewertbar', 'Bemängelbar'];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any[]>('/api/bewertungen/leistungsbewertungSozialverhalten').subscribe({
      next: (data) => this.sozialverhaltenInhalte = data,
      error: (err) => console.error('Fehler beim Laden der Sozialverhalten-Daten', err)
    });
  }

  onNoteChange(id: number, note: string) {
    this.noten[id] = note;
    this.notenGeaendert.emit(this.noten);
  }
}


import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-noten-tabelle',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './noten-tabelle.component.html'
})
export class NotenTabelleComponent {
  @Input() noten: { [id: number]: string } = {};
  @Output() notenGeaendert = new EventEmitter<{ [id: number]: string }>();

  leistungsbewertungInhalte: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any[]>('/api/bewertungen/leistungsbewertungInhalte').subscribe({
      next: (data) => {
        this.leistungsbewertungInhalte = data;
      },
      error: (err) => console.error('Fehler beim Laden der Inhalte', err)
    });
  }

  onNoteChange(id: number, note: string) {
    this.noten[id] = note;
    this.notenGeaendert.emit(this.noten);
  }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { AzubiService } from '../../services/azubi.service';

@Component({
  selector: 'app-azubi-detail',
  templateUrl: './azubi-detail.component.html',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule
  ],
  styleUrls: ['./azubi-detail.component.css']
})
export class AzubiDetailComponent implements OnInit {
  azubi: any;
  azubiId!: number;

  referate: any[] = [];
  schulungen: any[] = [];

  bewertungen: any[] = [];

  zeigeReferatDropdown = false;
  zeigeSchulungDropdown = false;

  vermittelteUnterpunkte: string[] = [];


  constructor(
    private route: ActivatedRoute,
    private azubiService: AzubiService,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.azubiId = +this.route.snapshot.paramMap.get('id')!;

    this.azubiService.getAzubiById(this.azubiId).subscribe(data => {
      this.azubi = data;
    });

    this.http.get<any[]>(`/api/bewertungen?azubiId=${this.azubiId}`).subscribe(data => {
      this.bewertungen = data;
    });

    // 🔁 Neue vermittelte Unterpunkte laden
    this.http.get<string[]>(`/api/vermittlungsstruktur/alle-vermittelten/${this.azubiId}`).subscribe(data => {
      this.vermittelteUnterpunkte = data;
    });
  }

  bewertungErstellen(typ: 'referat' | 'schulung') {
    if (typ === 'referat') {
      this.http.get<any[]>('/api/referate').subscribe(data => {
        this.referate = data;
        this.zeigeReferatDropdown = true;
        this.zeigeSchulungDropdown = false;
      });
    } else {
      this.http.get<any[]>('/api/schulungen').subscribe(data => {
        this.schulungen = data;
        this.zeigeSchulungDropdown = true;
        this.zeigeReferatDropdown = false;
      });
    }
  }

  referatAuswaehlen(event: Event) {
    const selectedId = +(event.target as HTMLSelectElement).value;
    this.router.navigate(['/bewertung/neu'], {
      queryParams: {
        azubiId: this.azubiId,
        referatId: selectedId
      }
    });
  }

  schulungAuswaehlen(event: Event) {
    const selectedId = +(event.target as HTMLSelectElement).value;
    this.router.navigate(['/bewertung/neu'], {
      queryParams: {
        azubiId: this.azubiId,
        schulungId: selectedId
      }
    });
  }

  // Beispiel für spätere Bearbeitung
  bearbeiten(bewertungId: number) {
    this.router.navigate(['/bewertung/bearbeiten'], {
      queryParams: {
        id: bewertungId
      }
    });
  }

  bewertungAnzeigen(bewertung: any) {
    const queryParams: any = {
      azubiId: this.azubiId,
    };

    if (bewertung.referat?.referat_id) {
      queryParams.referatId = bewertung.referat.referat_id;
    }

    if (bewertung.schulung?.schulung_id) {
      queryParams.schulungId = bewertung.schulung.schulung_id;
    }

    this.router.navigate(['/bewertung/neu'], { queryParams });
  }

}



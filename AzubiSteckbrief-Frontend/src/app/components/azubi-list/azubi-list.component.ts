import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AzubiService } from '../../services/azubi.service';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-azubi-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './azubi-list.component.html',
  styleUrls: ['./azubi-list.component.css']
})
export class AzubiListComponent implements OnInit {
  azubis: any[] = [];
  azubiJahre: number[] = [];

  constructor(private azubiService: AzubiService) {}

  ngOnInit() {
    this.azubiService.getAzubis().subscribe((data: any[]) => {
      this.azubis = data;
      this.azubiJahre = [...new Set(data.map(a => a.lehrjahr))].sort();
    });
  }

  getAzubisByJahr(jahr: number) {
    return this.azubis.filter(azubi => azubi.lehrjahr === jahr);
  }
}

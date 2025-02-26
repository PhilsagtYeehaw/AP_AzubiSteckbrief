import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AzubiService } from '../../services/azubi.service';

@Component({
  selector: 'app-azubi-detail',
  templateUrl: './azubi-detail.component.html',
  standalone: true,
  styleUrls: ['./azubi-detail.component.css']
})
export class AzubiDetailComponent implements OnInit {
  azubi: any;

  constructor(private route: ActivatedRoute, private azubiService: AzubiService) {}

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.azubiService.getAzubiById(id).subscribe(data => this.azubi = data);
  }
}

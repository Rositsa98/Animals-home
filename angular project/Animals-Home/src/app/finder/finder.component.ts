import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NavbarService } from '../navigation/navbar.service';
import { PetAdDto } from '../model/petAdDto';
import { AdService } from '../services/ad/ad.service';
import { Gender } from '../model/gender';
import { AlertService } from '../alert/alert.service';

@Component({
  selector: 'app-finder',
  templateUrl: './finder.component.html',
  styleUrls: ['./finder.component.scss']
})
export class FinderComponent implements OnInit {
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  owners: string[] = ['User', 'Shelter'];
  ages = Array.from([...Array(20).keys()].map(index => index + 1));
  genderTypes: string[] = Object.keys(Gender);
  //TODO
  habits: string[] = ['Dogs', 'Cats', 'Kids', 'House trained', 'Neutered'];


  petAds: PetAdDto[] = [];

  constructor(private navigation: NavbarService, private alert: AlertService, private activatedRoute: ActivatedRoute, private adService: AdService, private router: Router) {
    activatedRoute.params.subscribe((val) => {
      this.getAdsByType(val.type);
    });
  }

  ngOnInit() {
    this.navigation.show();
  }

  getAdsByType(curreentPetType: string) {
    let newAds: PetAdDto[] = [];
    this.adService.getPetAdsByType(curreentPetType.toLocaleUpperCase()).subscribe((ads) => {
      newAds = ads;
      this.petAds = newAds;
    },
      (error) => {
        this.router.navigate(['error/internal-server-error']);
      });
  }

  addPetAdToFavorites(id: number) {
    this.adService.addPetAdToFavorites(id).subscribe(() => {
      this.alert.success("<strong>Success!</strong> That was added to Favorites.", this.options)
    },
      (error) => {
        this.router.navigate(['error/internal-server-error']);
      });
  }
}

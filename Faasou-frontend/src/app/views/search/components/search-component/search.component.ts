import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-search-results',
  templateUrl: 'search.component.html',
  styleUrls: ['search.component.css']
})
export class SearchComponent implements OnInit {
  @Input() globalSearchResults: any;
  @Input() isLoading: boolean;

  public allImages: any[] = [];

  public firstColumnImages: any[] = [];

  public secondColumnImages: any[] = [];

  public thirdColumnImages: any[] = [];

  ngOnInit(): void {}

  public initializeColumnImages() {

    var allImagesSize = this.allImages.length;
    const allImagesSizeDividedBy3 =Math.floor(allImagesSize/3);

    var indiceTab=0;

    do{
      this.firstColumnImages[indiceTab] = this.allImages[indiceTab];
      indiceTab++;
    }while(( indiceTab % allImagesSizeDividedBy3) !=0 )

     do{
      this.secondColumnImages[indiceTab] = this.allImages[indiceTab];
      indiceTab++;
    }while((indiceTab%allImagesSizeDividedBy3) !=0 )


    do{
      this.thirdColumnImages[indiceTab] = this.allImages[indiceTab];
      indiceTab++;
    }while(indiceTab < allImagesSize )
  }

}

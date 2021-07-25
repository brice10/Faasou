import {Component} from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { environment } from '../../../../../environments/environment';
import { ISearchModel } from '../../../../models/search.model';
import { SearchService } from '../../../../services/search.service';
import { SEARCH_TYPES } from '../../../../utils/constants';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html',
  styleUrls: ['./default-layout.component.css']
})
export class DefaultLayoutComponent {
  public sidebarMinimized = false;
  public form = this.fb.group({});
  public isLoading: boolean = false;
  public allSearchResults: any[] = [];
  public imagesSearchResults: any[] = [];

  public firstColumnImages: any[] = [];

  public secondColumnImages: any[] = [];

  public thirdColumnImages: any[] = [];


  constructor(
    private fb: FormBuilder,
    private searchService: SearchService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const input = this.route.snapshot.queryParamMap.get('input');
    this.initForm(input);
    this.search();
  }

  toggleMinimize(e) {
    this.sidebarMinimized = e;
  }

  get f() {
    return this.form.controls;
  }

  public initForm(input?:string): void {
    this.form = this.fb.group({
      input: [input],
    })
  }

  public search(cx?: string, searchType?: string): void {
    const input: string = this.f.input.value;
    if(input.trim() != '') {
      this.isLoading = true;
      const searchModel: ISearchModel = {
        q: input,
        cx: cx? cx: environment.defaultCx,
        searchType: searchType? searchType: SEARCH_TYPES.SEARCH_TYPE_UNDEFINED
      }
      this.searchService.search(searchModel).toPromise().then(
        (result) => {
          if (result) {
            this.allSearchResults = result;
            console.log(result);
          }
        }
      ).catch(
        (error) => {
          console.log(error);
        }
      ).finally(
        () => {
          this.isLoading = false;
        }
      )
      // Recherche des images
      searchModel.searchType = SEARCH_TYPES.IMAGE;
      this.searchService.search(searchModel).toPromise().then(
        (result) => {
          if (result) {
            this.imagesSearchResults = result.items;
            this.initializeColumnImages();
            console.log(result);
          }
        }
      ).catch(
        (error) => {
          console.log(error);
        }
      ).finally(
        () => {
          this.isLoading = false;
        }
      )
    }
  }

  public clear(): void {
    this.form.reset();
  }

  public initializeColumnImages() {

    var allImagesSize = this.imagesSearchResults.length;
    const allImagesSizeDividedBy3 = Math.floor(allImagesSize/3);

    this.firstColumnImages.push(this.imagesSearchResults[0])
    var indiceTab=1;
    while((indiceTab % allImagesSizeDividedBy3) !=0 ) {
      this.firstColumnImages.push(this.imagesSearchResults[indiceTab]);
      indiceTab++;
    }

    this.secondColumnImages.push(this.imagesSearchResults[indiceTab]);
    indiceTab++;
    while((indiceTab%allImagesSizeDividedBy3) !=0 ) {
      this.secondColumnImages.push(this.imagesSearchResults[indiceTab]);
      indiceTab++;
    }


    while(indiceTab < allImagesSize ) {
      this.thirdColumnImages.push(this.imagesSearchResults[indiceTab]);
      indiceTab++;
    }
  }
}

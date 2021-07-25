import {Component} from '@angular/core';
import { FormBuilder } from '@angular/forms';
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

  constructor(
    private fb: FormBuilder,
    private searchService: SearchService
  ) {}

  ngOnInit(): void {
    this.initForm();
  }

  toggleMinimize(e) {
    this.sidebarMinimized = e;
  }

  get f() {
    return this.form.controls;
  }

  public initForm(): void {
    this.form = this.fb.group({
      input: [''],
    })
  }

  public search(cx?: string, searchType?: string): void {
    this.isLoading = true;
    const input: string = this.f.input.value;
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
  }

  public clear(): void {
    this.form.reset();
  }
}

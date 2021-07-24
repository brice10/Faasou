import {Component} from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { SearchService } from '../../../../services/search.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html',
  styleUrls: ['./default-layout.component.css']
})
export class DefaultLayoutComponent {
  public sidebarMinimized = false;
  public form = this.fb.group({});

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

  public search(): void {
    const input: string = this.f.input.value;
    this.searchService.search(input).toPromise().then(
      (result) => {
        if (result) {
          console.log(result);
        }
      }
    ).catch(
      (error) => {
        console.log(error);
      }
    )
  }

  public clear(): void {
    this.form.reset();
  }
}

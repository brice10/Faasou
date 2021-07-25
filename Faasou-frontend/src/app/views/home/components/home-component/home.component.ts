import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NavigationExtras, Router } from '@angular/router';

@Component({
  templateUrl: 'home.component.html',
  styleUrls: ['home.component.css']
})
export class HomeComponent implements OnInit {
  public form = this.fb.group({});

  constructor(
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initForm();
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
    const params: NavigationExtras = {
      queryParams: {
        'input': input
      }
    }
    this.router.navigate(['/search'], params);
  }

  public clear(): void {
    this.form.reset();
  }
}

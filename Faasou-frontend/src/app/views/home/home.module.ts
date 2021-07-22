import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';

import { HomeComponent } from './components/home-component/home.component';
import { HomeRoutingModule } from './home-routing.module';
import { DefaultLayoutComponent } from './components/default-layout';

const APP_CONTAINERS = [
  DefaultLayoutComponent
];

import {
  AppHeaderModule,
  AppFooterModule,
} from '@coreui/angular';
import { CommonModule } from '@angular/common';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HomeRoutingModule,
    ChartsModule,
    BsDropdownModule,
    ButtonsModule.forRoot(),
    AppHeaderModule,
    AppFooterModule
  ],
  declarations: [
    ...APP_CONTAINERS,
    HomeComponent
  ]
})
export class HomeModule { }

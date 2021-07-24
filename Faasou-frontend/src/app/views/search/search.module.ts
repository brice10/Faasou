import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { TabsModule } from 'ngx-bootstrap/tabs';

import { SearchComponent } from './components/search-component/search.component';
import { SearchRoutingModule } from './search-routing.module';
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
    SearchRoutingModule,
    ChartsModule,
    BsDropdownModule,
    ButtonsModule.forRoot(),
    AppHeaderModule,
    AppFooterModule,
    TabsModule,

  ],
  declarations: [
    ...APP_CONTAINERS,
    SearchComponent
  ]
})
export class SearchModule { }

import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { ISearchModel } from '../models/search.model';

@Injectable({
  providedIn: 'root'
})
export class SearchService implements OnInit {

  constructor(
    private http: HttpClient) { }

  ngOnInit() { }

  search(model: ISearchModel): Observable<any> {
    return this.http.post<any>(`${environment.basePath}/search`, model);
  }
}

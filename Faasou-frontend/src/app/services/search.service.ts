import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService implements OnInit {

  constructor(
    private http: HttpClient) { }

  ngOnInit() { }

  search(q: string): Observable<any> {
    return this.http.get<any>(`${environment.searchApiBasePath}?q=${q}&key=${environment.searchApiKey}`, {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*',
      })
    });
  }
}

import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-search-results',
  templateUrl: 'search.component.html',
  styleUrls: ['search.component.css']
})
export class SearchComponent implements OnInit {
  @Input() globalSearchResults: any;
  @Input() isLoading: boolean;

  ngOnInit(): void {}
}

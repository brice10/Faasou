import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-search-results',
  templateUrl: 'search.component.html',
  styleUrls: ['search.component.css']
})
export class SearchComponent implements OnInit {
  @Input() globalSearchResults: any;
  @Input() allImages: any[] = [];
  @Input() isLoading: boolean;
  @Input() firstColumnImages: any[] = [];
  @Input() secondColumnImages: any[] = [];
  @Input() thirdColumnImages: any[] = [];

  ngOnInit(): void {}

  
}

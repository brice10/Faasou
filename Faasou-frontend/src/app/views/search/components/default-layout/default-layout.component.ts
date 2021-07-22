import {Component} from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html',
  styleUrls: ['./default-layout.component.css']
})
export class DefaultLayoutComponent {
  public sidebarMinimized = false;

  toggleMinimize(e) {
    this.sidebarMinimized = e;
  }
}

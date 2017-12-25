import { Component, OnInit } from '@angular/core';

declare const $: any;
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: 'dashboard', title: 'Dashboard',  icon: 'dashboard', class: '' },
    { path: 'purchases', title: 'Purchases',  icon: 'shopping_cart', class: '' },
    { path: 'customers', title: 'Customers',  icon: 'people', class: '' },
    { path: 'employees', title: 'Employees',  icon: 'contacts', class: '' },
    { path: 'products',     title: 'Products',  icon: 'shop_two', class: '' },
    { path: 'suppliers', title: 'Suppliers',  icon: 'local_shipping', class: '' },
    { path: 'logs', title: 'Logs',  icon: 'library_books', class: '' },
    // { path: 'upgrade', title: 'Upgrade to PRO',  icon: 'unarchive', class: 'active-pro' },
    // { path: 'upgrade', title: 'Upgrade to PRO',  icon: 'unarchive', class: 'active-pro' },
    // { path: 'upgrade', title: 'Upgrade to PRO',  icon: 'unarchive', class: 'active-pro' },
    
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor() { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }
  isMobileMenu() {
      if ($(window).width() > 991) {
          return false;
      }
      return true;
  };
}

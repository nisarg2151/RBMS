import { Component, OnInit, AfterViewInit } from '@angular/core';
import { GetDataService } from '../get-data.service';

declare const $: any;

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit, AfterViewInit {
  products: any = {};
  discounts: any = {};
  productsHeader: any = [];
  discountsHeader: any = [];

  constructor(private getDataService: GetDataService) { }

  ngOnInit() {
    this.getProducts();
    this.getDiscounts();
    console.log('Products');
  }
  ngAfterViewInit() {

    $('div.card-content').mCustomScrollbar({
      theme: 'minimal-dark',
      axis: 'yx'
    })
  }

  getProducts(): void {
    this.getDataService.getProductsData()
      .subscribe(products => {
        console.log('inside Products Service');
        this.products = products;
        console.log(this.products);
        this.productsHeader = Object.keys(this.products.allProducts[0]);
        console.log(this.productsHeader);
      });
  }

  getDiscounts(): void {
    this.getDataService.getDiscountsData()
      .subscribe(discounts => {
        console.log('inside Discounts Service');
        this.discounts = discounts;
        console.log(this.discounts);
        this.discountsHeader = Object.keys(this.discounts.allDiscounts[0]);
        console.log(this.discountsHeader);

      });
  }



}

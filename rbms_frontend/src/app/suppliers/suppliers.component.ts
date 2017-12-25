import { Component, OnInit,AfterViewInit } from '@angular/core';
import { GetDataService } from '../get-data.service';

declare const $: any;

@Component({
  selector: 'app-suppliers',
  templateUrl: './suppliers.component.html',
  styleUrls: ['./suppliers.component.css']
})
export class SuppliersComponent implements OnInit ,AfterViewInit{
    suppliers: any = {};
    supply: any = {};
    suppliersHeader: any = [];
    supplyHeader: any = [];

    constructor(private getDataService: GetDataService) { }

    ngOnInit() {
      this.getSuppliers();
      this.getSupply();
      console.log('supplier');

    }

    ngAfterViewInit() {
      
                  $('div.card-content').mCustomScrollbar({
                      theme: 'minimal-dark',
                      axis: 'yx'
                  })
              }

    getSuppliers(): void {
      this.getDataService.getSuppliersData()
          .subscribe(suppliers => {
            console.log('inside supplier Service');
            this.suppliers = suppliers;
            console.log(this.suppliers);
            this.suppliersHeader = Object.keys(this.suppliers.allSuppliers[0]);
            console.log(this.suppliersHeader);
          });
    }

    getSupply(): void {
      this.getDataService.getSupplyData()
          .subscribe(supply => {
            console.log('inside supply Service');
            this.supply = supply;
            console.log(this.supply);
            this.supplyHeader = Object.keys(this.supply.allSupplies[0]);
            console.log(this.supplyHeader);
          });
    }



  }


import { Component, OnInit, AfterViewInit } from '@angular/core';
import { GetDataService } from '../get-data.service';
declare const $: any;

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit,AfterViewInit {
  message: any;
  addedFlag = false;

  customers: any = {};
  customersHeader: any = [];
  customer: any = {};
  constructor(private getDataService: GetDataService) { }

  ngOnInit() {
    this.getCustomers();
    console.log('customers');

  }
  ngAfterViewInit() {

            $('div.card-content').mCustomScrollbar({
                theme: 'minimal-dark',
                axis: 'yx'
            })
        }

  getCustomers(): void {
    this.getDataService.getCustomersData()
      .subscribe(customers => {
        console.log('inside Service');
        this.customers = customers;
        console.log('inside Service customers');
        console.log(this.customers);
        this.customersHeader = Object.keys(this.customers.allCustomers[0]);
        console.log(this.customersHeader);
      });
  }
  addCustomer() {
    console.log(this.customer);
    this.getDataService.addCustomer(this.customer)
      .subscribe(
        (data) => {
                this.addedFlag = true;
                // this.message = JSON.parse(data._body);
                $('#messageModal').modal('show');
                console.log('Added Customer');
                 },
        (err) => {
                 this.addedFlag = false;
                 this.message = err._body;
                 $('#messageModal').modal('show');
                 console.log('error addding customer');
                 console.log(err);
                }
      );

  }



}

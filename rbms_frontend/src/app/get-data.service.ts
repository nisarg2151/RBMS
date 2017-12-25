import { Injectable, Inject } from '@angular/core';
import { Http, Headers, RequestOptions, Response, RequestMethod, Request } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class GetDataService {
  search: any;
  ipAddress: any = 'http://localhost:8080/rbms_backend';

  constructor(private http: Http) { }

  public setSearch(search) {
    this.search = search;
    console.log(this.search);
  }

  public getSearch() {
    console.log(this.search);
    return this.search;
  }
  getCountData() {
    return this.http.get(this.ipAddress + '/rbms/general/count').map(res => res.json());
  }
  getEmployeeData() {
    return this.http.get(this.ipAddress + '/rbms/employee/all').map(res => res.json());
  }
  getProductsData() {
    return this.http.get(this.ipAddress + '/rbms/product/all').map(res => res.json());
  }
  getDiscountsData() {
    return this.http.get(this.ipAddress + '/rbms/discount/all').map(res => res.json());
  }
  getSuppliersData() {
    return this.http.get(this.ipAddress + '/rbms/supplier/all').map(res => res.json());
  }

  getSupplyData() {
    return this.http.get(this.ipAddress + '/rbms/supply/all').map(res => res.json());
  }

  getCustomersData() {
    return this.http.get(this.ipAddress + '/rbms/customer/all').map(res => res.json());
  }

  getDiscounts() {
    return this.http.get(this.ipAddress + '/rbms/supply/all').map(res => res.json());
  }

  getPurchasesData() {
    return this.http.get(this.ipAddress + '/rbms/purchase/all').map(res => res.json());
  }
  getLogsData() {
    return this.http.get(this.ipAddress + '/rbms/logs/all').map(res => res.json());
  }

  addCustomer(request) {

    let postheaders = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' });
    let options = new RequestOptions({ headers: postheaders });
    return this.http.post(this.ipAddress + '/rbms/customer/add', JSON.stringify(request), options).map(res => res.json());

  }

  addPurchase(request) {

    let postheaders = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' });
    let options = new RequestOptions({ headers: postheaders });
    return this.http.post(this.ipAddress + '/rbms/purchase/add', JSON.stringify(request), options).map(res => res.json());

  }

  deletePurchase(request) {

    let postheaders = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' });
    let options = new RequestOptions({ headers: postheaders });
    return this.http.post(this.ipAddress + '/rbms/purchase/delete', JSON.stringify(request), options).map(res => res.json());

  }

  getPurchaseSavingData(request) {
    let postheaders = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' });
    let options = new RequestOptions({ headers: postheaders });
    return this.http.post(this.ipAddress + '/rbms/purchase/saving', JSON.stringify(request), options).map(res => res.json());

  }

  getMonthlySalesData(request) {
    let postheaders = new Headers({
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    });
    let options = new RequestOptions({ headers: postheaders });
    return this.http.post(this.ipAddress + '/rbms/employee/monthlySaleActivity',
      JSON.stringify(request),
      options).map(res => res.json());
  }
}

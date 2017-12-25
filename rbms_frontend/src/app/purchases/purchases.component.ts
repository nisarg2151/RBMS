import { Component, OnInit, AfterViewInit } from '@angular/core';
import { GetDataService } from '../get-data.service';
declare const $: any;

@Component({
  selector: 'app-purchases',
  templateUrl: './purchases.component.html',
  styleUrls: ['./purchases.component.css']
})
export class PurchasesComponent implements OnInit, AfterViewInit {
  errorFlag: boolean;
  modalMessage: any;
  deletedFlag: boolean;
  purchases: any = {};
  purchase: any = {};
  purchasesHeader: any = [];
  addedFlag = false;
  search = this.getDataService.getSearch;

  constructor(private getDataService: GetDataService) { }

  ngOnInit() {
    this.getPurchases();
    console.log('purchases');
  }


  ngAfterViewInit() {

    $('div.card-content').mCustomScrollbar({
      theme: 'minimal-dark',
      axis: 'yx'
    })
  }
  // ngDoCheck(){
  //   this.search = this.getDataService.getSearch();
  //   console.log(this.search);
  //   // tslint:disable-next-line:no-trailing-whitespace
  // }	


  getPurchases(): void {
    this.getDataService.getPurchasesData()
      .subscribe(purchases => {
        console.log("inside Service");
        this.purchases = purchases;
        console.log('inside Service purchases');
        console.log(this.purchases);
        this.purchasesHeader = Object.keys(this.purchases.allPurchases[0]);
        console.log(this.purchasesHeader);
      });
  }

  addPurchase() {
    console.log(this.purchase);
    this.getDataService.addPurchase(this.purchase)
      .subscribe(data => {
        this.errorFlag = false;
        this.addedFlag = true;
        console.log(data);
        
        this.modalMessage = data.SuccessMessage;
        $('#messageModal').modal('show');
        console.log('Add Purchase');
      },
      err => {
        this.errorFlag = true;
        this.addedFlag = true;
        this.modalMessage = err._body;
        $('#messageModal').modal('show');
        console.log(err._body);
      }

      );
  }

  deletePurchase() {
    console.log(this.purchase);
    this.getDataService.deletePurchase(this.purchase)
      .subscribe(data => {
        this.errorFlag = false;
        this.addedFlag = false;
        console.log(data);
        this.modalMessage = data.SuccessMessage;
        $('#messageModal').modal('show');
        console.log('Add Purchase');
      },
      err => {
        this.errorFlag = true;
        this.addedFlag = false;
        $('#messageModal').modal('show');
        this.modalMessage = err._body;
        console.log('Error while deleting purchase' + this.purchase.puid +
          err._body);
      }

      );

  }


}

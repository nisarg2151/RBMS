import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import * as Chartist from 'chartist';
import { Location, LocationStrategy, PathLocationStrategy, PopStateEvent } from '@angular/common';
import 'rxjs/add/operator/filter';
import { NavbarComponent } from '../components/navbar/navbar.component';
import { Router, NavigationEnd, NavigationStart } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import PerfectScrollbar from 'perfect-scrollbar';
import { GetDataService } from '../get-data.service';

declare const $: any;


@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, AfterViewInit {
    emailsSubscriptionChart: Chartist.ChartistStatic;
    dailySalesChart: Chartist.ChartistStatic;
    showWidgets = false;
    graphShow: boolean;
    count: any;
    showSales = false;
    showSavings = false;
    salesDataHeader: string[];
    salesData: any;
    savingForPUR: any;
    totalPrice: any = [];
    purIDs: any = [];
    purchasesHeader: any;
    purchases: any;
    getSavingPUR: any = {};
    monthlySales: any = {};
    constructor(private getDataService: GetDataService,private router : Router) { }

    getSaving() {
        this.getDataService.getPurchaseSavingData(this.getSavingPUR)
            .subscribe(data => {
                //   console.log("inside Service");
                this.savingForPUR = data;
                this.showSavings = true;
                console.log(this.savingForPUR);
                $('#savingsModal').modal('show');
                this.router.navigateByUrl('/dashboard');
            },
            err => {
                this.showSavings = false;
                console.log(err);
            });
    }

    getMonthlySales() {
        this.getDataService.getMonthlySalesData(this.monthlySales)
            .subscribe(data => {
                //   console.log("inside Service");
                this.salesData = data;
                this.salesDataHeader = Object.keys(this.salesData.salesPerEmployee[0]);
                this.showSales = true;
                console.log(this.salesDataHeader);
                $('#salesModal').modal('show');
            },
            err => {
                this.showSales = false;
                console.log(err);
            });
    }

    getPurchases(): void {
        this.getDataService.getPurchasesData()
            .subscribe(purchases => {
                //   console.log("inside Service");
                this.purchases = purchases;
                console.log(this.purchases);
                this.purchasesHeader = Object.keys(this.purchases.allPurchases[0]);
                console.log(this.purchasesHeader);

                for (let purID of this.purchases.allPurchases) {
                    this.purIDs.push(purID.pur);
                }
                for (let totalPrice of this.purchases.allPurchases) {
                    this.totalPrice.push(totalPrice.total_price);
                }


      const datalineChart: any = {
        labels: this.purIDs,
       series: [this.totalPrice]
    };

   const optionslineChart: any = {
        lineSmooth: Chartist.Interpolation.cardinal({
            tension: 0
        }),
        low: 0,
        high: 800, 
        chartPadding: { top: 0, right: 0, bottom: 0, left: 0},
    }

    var lineChart = new Chartist.Line('#lineChart', datalineChart, optionslineChart);

    this.startAnimationForLineChart(lineChart);



    /* ----------==========    Bar Chart initialization    ==========---------- */

    var dataBarChart = {
        labels: this.purIDs,
        series: [this.totalPrice]
    };
    var optionsBarChart = {
        axisX: {
            showGrid: false
        },
        low: 0,
        high: 1000,
        chartPadding: { top: 0, right: 5, bottom: 0, left: 0}
    };
    var responsiveOptions: any[] = [
      ['screen and (max-width: 640px)', {
        seriesBarDistance: 5,
        axisX: {
          labelInterpolationFnc: function (value) {
            return value[0];
          }
        }
      }]
    ];
    var barChart = new Chartist.Bar('#barChart', dataBarChart, optionsBarChart, responsiveOptions);

    //start animation for the Emails Subscription Chart
    this.startAnimationForBarChart(barChart);
                
            });
    }
    getCount(): void {
        this.getDataService.getCountData()
            .subscribe(count => {
                //   console.log("inside Service");
                this.count = count;
                this.showWidgets = true;
                console.log(this.count);
            });
    }
    showGraphs():void{
        this.graphShow=true;
        this.getPurchases();
    }


    startAnimationForLineChart(chart) {
        let seq: any, delays: any, durations: any;
        seq = 0;
        delays = 80;
        durations = 500;

        chart.on('draw', function (data) {
            if (data.type === 'line' || data.type === 'area') {
                data.element.animate({
                    d: {
                        begin: 600,
                        dur: 700,
                        from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
                        to: data.path.clone().stringify(),
                        easing: Chartist.Svg.Easing.easeOutQuint
                    }
                });
            } else if (data.type === 'point') {
                seq++;
                data.element.animate({
                    opacity: {
                        begin: seq * delays,
                        dur: durations,
                        from: 0,
                        to: 1,
                        easing: 'ease'
                    }
                });
            }
        });

        seq = 0;
    };

    startAnimationForBarChart(chart) {
        let seq2: any, delays2: any, durations2: any;

        seq2 = 0;
        delays2 = 80;
        durations2 = 500;
        
        chart.on('draw', function (data) {
            if (data.type === 'bar') {
                seq2++;
                data.element.animate({
                    opacity: {
                        begin: seq2 * delays2,
                        dur: durations2,
                        from: 0,
                        to: 1,
                        easing: 'ease'
                    }
                });
            }
        });

        seq2 = 0;
    };





    ngOnInit() {
        
        this.getCount();
  

        
    }



    ngAfterViewInit() {
        $('div.card-content').mCustomScrollbar({
            theme: 'minimal-dark',
            axis: 'yx'
        })
        $('div.card-header1').mCustomScrollbar({
            theme: 'minimal-dark',
            axis: 'yx'
        })

    }

}

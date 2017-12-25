import { Component, OnInit,AfterViewInit } from '@angular/core';
import { GetDataService } from '../get-data.service';

declare var $: any;
@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.css']
})
export class LogsComponent implements OnInit,AfterViewInit {


    logs: any = {};
    logsHeader: any = [];
    constructor(private getDataService: GetDataService) { }
  
    ngOnInit() {
      this.getLogs();
      console.log('logs');
    }

    ngAfterViewInit() {
      
                  $('div.card-content').mCustomScrollbar({
                      theme: 'minimal-dark',
                      axis: 'yx'
                  })
              }
    
    getLogs(): void {
      this.getDataService.getLogsData()
          .subscribe(logs => {
            console.log("inside Service");
            this.logs = logs;
            console.log('inside Service logs');
            console.log(this.logs);
            this.logsHeader = Object.keys(this.logs.allLogs[0]);
            console.log(this.logsHeader);
          });
    }
  
  
  }
//   constructor() { }
//   showNotification(from, align){
//       const type = ['','info','success','warning','danger'];

//       const color = Math.floor((Math.random() * 4) + 1);

//       $.notify({
//           icon: "logs",
//           message: "Welcome to <b>RBMS</b> - Database Systems Project2."

//       },{
//           type: type[color],
//           timer: 4000,
//           placement: {
//               from: from,
//               align: align
//           }
//       });
//   }
//   ngOnInit() {
//   }

// }

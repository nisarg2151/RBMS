import { Component, OnInit ,AfterViewInit} from '@angular/core';
import { GetDataService } from '../get-data.service';
declare const $: any;

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit,AfterViewInit {
  employees: any = {};
  employeesHeader: any = [];
  constructor(private getDataService: GetDataService) { }

  ngOnInit() {
    this.getEmployees();
    console.log('Employees');

  }

  ngAfterViewInit() {
    
                $('div.card-content').mCustomScrollbar({
                    theme: 'minimal-dark',
                    axis: 'yx'
                })
            }

  getEmployees(): void {
    this.getDataService.getEmployeeData()
        .subscribe(employees => {
          console.log("inside Service");
          this.employees = employees;
          console.log('inside Service Employees');
          console.log(this.employees);
          console.log(this.employees);
          this.employeesHeader = Object.keys(this.employees.allEmployees[0]);
          console.log(this.employeesHeader);
        });
  }


}

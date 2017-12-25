# RBMS
## Dashboard For Typical Retail Business Management System

[![RBMS Dashboard](https://github.com/nisarg2151/RBMS/blob/master/rbms_frontend/src/assets/img/DashboardSS.png)](#RBMS)
##  Prerequisites & Setup
- ### Frontend: 
  - Download "rbms_frontend" folder at desired location.
  - Install node.js from https://nodejs.org/en/download/
  - Install Angular CLI by running "npm install -g @angular/cli" on command prompt
  - Go to location where you extracted the directory in command prompt
  - Run command "npm install" to download depndencies related to project
  - Run command "ng serve" to deploy UI on node server.
  - Open http://localhost:4200/ on your browser to access the UI.
  - User Interface is kind of simple and self explanatory to navigate.

- ### Backend:
  - You will need Java,Apache Tomcat,Maven,Eclipse installed on your system to proceed further. 
  - Download "rbms_backend" folder at desired location. 
  - Select Import Maven Project from Eclipse JEE IDE. Browse to directory where you extracted your zip file.
  - POM.XML will be detected and project will be imported automatically.
  - Select Run as Maven Install by right clicking on project in eclipse.
  - Select Run as on Server --> You will need to setup Apache Tomcat 8+ on your eclipse to run this.
  - REST web services are now ready to be consumed by UI 

##  Data Definition
- Following Tables will be used in system. They are self-explanatory given some knowledge of Oracle SQL.

   - Employees (eid, name, telephone#, email)  
   - Customers (cid, name, telephone#, visits_made, last_visit_date)  
   - Products (pid, name, qoh, qoh_threshold, original_price, discnt_category)  
   - Discounts (discnt_category, discnt_rate)
   - Suppliers (sid, name, city, telephone#, email)
   - Supplies (sup#, pid, sid, sdate, quantity)
   - Purchases (pur#, eid, pid, cid, ptime, qty, total_price)
   - Logs (log#, user_name, operation, op_time, table_name, tuple_pkey)
 
 ## Credits & References
  - Thanks to [creativetimofficial](https://github.com/creativetimofficial) for awesome admin template [material-dashboard-angular](https://github.com/creativetimofficial/material-dashboard-angular).
  - Thanks to [mkyong](http://www.mkyong.com/) for Spring Rest Tutorials.

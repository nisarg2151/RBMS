import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchpipe'
})
export class SearchPipe implements PipeTransform {

  transform(data: any, args: string , args2 :any[]): any {
    let filteredData =data;
    let tempArray:Array<any> = [];
    if(args!=undefined && args.length>0  && Array.isArray(data)){
      filteredData.forEach((item:any) => {
        let flag = false;
        if(args2!=undefined && Array.isArray(args2)){
        args2.forEach((column) => {
        if(typeof item[column.name.trim()] == "string"){
          if (item[column.name.trim()].match(args)) {
            //console.log("data in pipe"+item[column] +"testing column"+ column);
            flag = true;
          }
        }
       if(typeof item[column.name] == "number"){
          if (item[column.name].toString().match(args)) {
            //console.log("data in pipe"+item[column] +"testing column"+ column);
            flag = true;
          }
        }

        else if(column.name==undefined && typeof item[column] == "string"){
          if (item[column].match(args)) {
            //console.log("data in pipe"+item[column] +"testing column"+ column);
            flag = true;
          }
        }
        });
}else{
  if(item.name!=undefined && item.name.toLowerCase().match(args.toLowerCase())){
      flag = true;
  }
}

        if (flag) {
          tempArray.push(item);
        }
      });
      filteredData = tempArray;
    }
    return filteredData;
  }
}

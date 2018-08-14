import { Component, OnInit } from '@angular/core';
import { VisitorCountService } from '../visitor-count.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-daily-chart',
  templateUrl: './daily-chart.component.html',
  styleUrls: ['./daily-chart.component.css'],
  outputs: ['selectedDate']
})
export class DailyChartComponent implements OnInit {
  constructor(private visitorCountService: VisitorCountService, private datePipe: DatePipe) { }

  ngOnInit() {
    this.visitorCountService.listDailyVisitorCount('d573c281-8f9f-43f9-8174-7fbe7f7428d8', this.weekStartDate, this.weekEndDate).subscribe(dailyVisitorCounts => {
      let data = new Array(1);
      data[0] = { data: new Array(7), label: '1栋A座西侧' }
      for (let x = 0; x < this.barChartLabels.length; x++) {
        for (let i = 0; i < dailyVisitorCounts.length; i++) {
          if (this.barChartLabels[x].replace(/\//g, '') == dailyVisitorCounts[i].countDateStr) {
            data[0].data[x] = dailyVisitorCounts[i].personCount;
            break;
          }
        }
      }
      this.barChartData = data;
    })
  }
  public barChartType: string = 'bar';

  public barChartLabels: string[] = this.getWeekDay();
  public barChartData: any[] = [{ data: [0], label: '' }];

  public selectedDate: Date;

  private weekStartDate: Date;
  private weekEndDate: Date;

  // events
  public chartClicked(e: any): void {
    if (e.active.length > 0) {
      this.selectedDate = new Date(e.active[0]._model.label);
    }
  }

  private getWeekDay(): string[] {
    let result = new Array();

    let now = new Date();                    //当前日期
    let nowDayOfWeek = now.getDay();         //今天本周的第几天
    let nowDay = now.getDate();              //当前日
    let nowMonth = now.getMonth();           //当前月
    let nowYear = now.getFullYear();             //当前年

    this.weekStartDate = new Date(nowYear, nowMonth, nowDay - 6);
    for (let i = 0; i < 7; i++) {
      this.weekEndDate = new Date(this.weekStartDate.getTime() + (i * 24 * 60 * 60 * 1000))
      let dateStr = this.datePipe.transform(this.weekEndDate, 'yyyy/MM/dd');
      result.push(dateStr);
    }

    return result;
  }
}

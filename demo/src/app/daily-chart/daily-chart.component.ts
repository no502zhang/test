import { Component, OnInit } from '@angular/core';
import { VisitorCountService } from '../visitor-count.service';

@Component({
  selector: 'app-daily-chart',
  templateUrl: './daily-chart.component.html',
  styleUrls: ['./daily-chart.component.css'],
  outputs: ['selectedDate']
})
export class DailyChartComponent implements OnInit {
  constructor(private visitorCountService: VisitorCountService) { }

  ngOnInit() {
    this.visitorCountService.listDailyVisitorCount(null).subscribe(dailyVisitorCounts => {
      let data = new Array(1);
      data[0] = { data: new Array(7), label: '1栋A座西侧' }
      for (let x = 0; x < this.barChartLabels.length; x++) {
        for (let i = 0; i < dailyVisitorCounts.length; i++) {
          if (this.barChartLabels[x] == dailyVisitorCounts[i].countDateStr) {
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

  public selectedDate;

  // events
  public chartClicked(e: any): void {
    console.log('chartClicked:');
    console.log(e);
    if (e.active.length > 0) {
      console.log(e.active[0]._model.label);
      let year = e.active[0]._model.label.substring(1, 4);
      console.log(year);
      
      this.selectedDate = e.active[0]._model.label;
    }

  }

  public chartHovered(e: any): void {
    console.log('chartHovered:');
    console.log(e);
  }

  private getWeekDay(): string[] {
    return ['20180730', '20180731', '20180801', '20180802', '20180803', '20180804', '20180805'];
  }
}

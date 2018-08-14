import { Component, OnInit, Input } from '@angular/core';
import { VisitorCountService } from '../visitor-count.service';

@Component({
  selector: 'app-time-chart',
  templateUrl: './time-chart.component.html',
  styleUrls: ['./time-chart.component.css']
})
export class TimeChartComponent implements OnInit {
  constructor(private visitorCountService: VisitorCountService) { }

  ngOnInit() {
  }

  @Input()
  set cDate(value: Date) {
    if (value) {
      this.visitorCountService.listTimeVisitorCount('d573c281-8f9f-43f9-8174-7fbe7f7428d8', value).subscribe(timeVisitorCounts => {
        let data = new Array(1);
        data[0] = { data: new Array(24), label: '1栋A座西侧' }
        for (let x = 0; x < this.lineChartLabels.length; x++) {
          for (let i = 0; i < timeVisitorCounts.length; i++) {
            if (this.lineChartLabels[x] == timeVisitorCounts[i].countHourStr.substring(8)) {
              data[0].data[x] = timeVisitorCounts[i].personCount;
              break;
            }
          }
        }
        this.lineChartData = data;
      })
    }
  }

  // lineChart
  public lineChartData: Array<any> = [{ data: [0], label: '' }];
  public lineChartLabels: Array<any> = ['0000', '0100', '0200', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300'];
  public lineChartOptions: any = {
    responsive: true
  };
  public lineChartLegend: boolean = true;
  public lineChartType: string = 'line';
}

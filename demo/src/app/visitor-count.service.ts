import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DatePipe } from '@angular/common';

import { Observable, of } from 'rxjs';

import { DailyVisitorCount, TimeVisitorCount } from './visitor-count.model';


@Injectable({
  providedIn: 'root'
})
export class VisitorCountService {

  constructor(private http: HttpClient, private datePipe: DatePipe) { }

  listDailyVisitorCount(cameraId: String, beginTime: Date, endTime: Date): Observable<DailyVisitorCount[]> {
    let beginTimeParam = this.datePipe.transform(beginTime, 'yyyy/MM/dd');
    let endTimeParam = this.datePipe.transform(endTime, 'yyyy/MM/dd 23:59:59');

    return this.http.get<DailyVisitorCount[]>('/visitors/dailyCount/list?cameraId=' + cameraId + '&beginTime=' + beginTimeParam + '&endTime=' + endTimeParam)
  }

  listTimeVisitorCount(cameraId: String, date: Date): Observable<TimeVisitorCount[]> {
    let beginTimeParam = this.datePipe.transform(date, 'yyyy/MM/dd');
    let endTimeParam = this.datePipe.transform(new Date(date.getTime() + (1 * 24 * 60 * 60 * 1000)), 'yyyy/MM/dd');

    return this.http.get<TimeVisitorCount[]>('/visitors/timeCount/list?cameraId=' + cameraId + '&beginTime=' + beginTimeParam + '&endTime=' + endTimeParam);
  }
}

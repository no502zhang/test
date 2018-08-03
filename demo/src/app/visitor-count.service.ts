import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { DailyVisitorCount, TimeVisitorCount } from './visitor-count.model';

@Injectable({
  providedIn: 'root'
})
export class VisitorCountService {

  constructor(private http: HttpClient) { }

  listDailyVisitorCount(cameraId:String): Observable<DailyVisitorCount[]> {
    return this.http.get<DailyVisitorCount[]>('/visitors/dailyCount/list?cameraId=d573c281-8f9f-43f9-8174-7fbe7f7428d8&beginTime=2018/08/01&endTime=2018/08/31')
  }

  listTimeVisitorCount(cameraId:String, date:Date): Observable<TimeVisitorCount[]> {
    return this.http.get<TimeVisitorCount[]>('/visitors/timeCount/list?cameraId=d573c281-8f9f-43f9-8174-7fbe7f7428d8&beginTime=2018/08/03&endTime=2018/08/04')
  }
}

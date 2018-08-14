import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { ChartsModule } from 'ng2-charts';

import { AppComponent } from './app.component';

import { DailyChartComponent } from './daily-chart/daily-chart.component';
import { TimeChartComponent } from './time-chart/time-chart.component';

import { DatePipe } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    DailyChartComponent,
    TimeChartComponent
  ],
  imports: [
    BrowserModule, CommonModule, HttpClientModule, ChartsModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }

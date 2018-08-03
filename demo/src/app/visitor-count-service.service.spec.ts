import { TestBed, inject } from '@angular/core/testing';

import { VisitorCountService } from './visitor-count.service';

describe('VisitorCountService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [VisitorCountService]
    });
  });

  it('should be created', inject([VisitorCountService], (service: VisitorCountService) => {
    expect(service).toBeTruthy();
  }));
});

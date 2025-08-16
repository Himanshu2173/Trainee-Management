import { TestBed } from '@angular/core/testing';

import { TraineeService } from './traineeService';

describe('Traineeservice', () => {
  let service: TraineeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TraineeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

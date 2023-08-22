import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StationStatisticsComponent } from './station-statistics.component';

describe('StationStatisticsComponent', () => {
  let component: StationStatisticsComponent;
  let fixture: ComponentFixture<StationStatisticsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StationStatisticsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StationStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

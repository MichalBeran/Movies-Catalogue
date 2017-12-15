import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectorIndexComponent } from './director-index.component';

describe('DirectorIndexComponent', () => {
  let component: DirectorIndexComponent;
  let fixture: ComponentFixture<DirectorIndexComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DirectorIndexComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DirectorIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

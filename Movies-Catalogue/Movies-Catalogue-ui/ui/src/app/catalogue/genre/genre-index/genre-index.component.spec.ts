import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GenreIndexComponent } from './genre-index.component';

describe('GenreIndexComponent', () => {
  let component: GenreIndexComponent;
  let fixture: ComponentFixture<GenreIndexComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GenreIndexComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GenreIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PdfFormComponent } from './pdf-form.component';

describe('PdfFormComponent', () => {
  let component: PdfFormComponent;
  let fixture: ComponentFixture<PdfFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PdfFormComponent]
    });
    fixture = TestBed.createComponent(PdfFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { SimpleChange } from '@angular/core';
import { of } from 'rxjs';
import { CourseCard } from './course-card.component';
import { EnrollmentService } from '../../services/enrollment.service';

describe('CourseCard', () => {
  let component: CourseCard;
  let fixture: ComponentFixture<CourseCard>;
  let mockEnrollmentService: any;

  beforeEach(async () => {
    mockEnrollmentService = {
      isEnrolled: jasmine.createSpy('isEnrolled').and.returnValue(of(false)),
      enroll: jasmine.createSpy('enroll').and.returnValue(of({})),
      unenroll: jasmine.createSpy('unenroll').and.returnValue(of({}))
    };

    await TestBed.configureTestingModule({
      imports: [CourseCard],
      providers: [
        { provide: EnrollmentService, useValue: mockEnrollmentService }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseCard);
    component = fixture.componentInstance;
    component.course = { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' };
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render course name from @Input', () => {
    fixture.detectChanges();
    const titleEl = fixture.debugElement.query(By.css('h3')).nativeElement;
    expect(titleEl.textContent).toContain('Data Structures');
  });

  it('should call onEnrollClick and emit event on button click', () => {
    spyOn(component.enrollRequested, 'emit');
    const enrollButton = fixture.debugElement.query(By.css('button.btn-primary')).nativeElement;
    enrollButton.click();
    fixture.detectChanges();
    expect(mockEnrollmentService.enroll).toHaveBeenCalledWith(1);
    expect(component.enrollRequested.emit).toHaveBeenCalledWith(1);
  });

  it('should log console message on ngOnChanges', () => {
    spyOn(console, 'log');
    const newCourse = { id: 1, name: 'Advanced Algorithms', code: 'CS101', credits: 4, gradeStatus: 'passed' };
    component.course = newCourse;
    component.ngOnChanges({
      course: new SimpleChange(null, newCourse, true)
    });
    expect(console.log).toHaveBeenCalled();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CourseSummary } from './course-summary.component';
import { CourseService } from '../../services/course.service';
import { EnrollmentService } from '../../services/enrollment.service';
import { of } from 'rxjs';

describe('CourseSummary', () => {
  let component: CourseSummary;
  let fixture: ComponentFixture<CourseSummary>;
  let mockCourseService: any;
  let mockEnrollmentService: any;

  beforeEach(async () => {
    mockCourseService = {
      getCourses: () => of([])
    };
    mockEnrollmentService = {
      getEnrolledCourses: () => of([])
    };

    await TestBed.configureTestingModule({
      imports: [CourseSummary],
      providers: [
        { provide: CourseService, useValue: mockCourseService },
        { provide: EnrollmentService, useValue: mockEnrollmentService }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseSummary);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

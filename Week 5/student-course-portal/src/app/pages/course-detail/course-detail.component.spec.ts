import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CourseDetail } from './course-detail.component';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CourseService } from '../../services/course.service';
import { of } from 'rxjs';

describe('CourseDetail', () => {
  let component: CourseDetail;
  let fixture: ComponentFixture<CourseDetail>;
  let mockCourseService: any;

  beforeEach(async () => {
    mockCourseService = {
      getCourseById: jasmine.createSpy('getCourseById').and.returnValue(of({
        id: 1, name: 'CS Course', code: 'CS101', credits: 4, gradeStatus: 'passed'
      }))
    };

    await TestBed.configureTestingModule({
      imports: [CourseDetail, RouterModule.forRoot([])],
      providers: [
        { provide: CourseService, useValue: mockCourseService },
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: {
                get: () => '1'
              }
            }
          }
        }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseDetail);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

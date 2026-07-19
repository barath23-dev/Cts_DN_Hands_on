import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Home } from './home.component';
import { CourseService } from '../../services/course.service';
import { of } from 'rxjs';

describe('Home', () => {
  let component: Home;
  let fixture: ComponentFixture<Home>;
  let mockCourseService: any;

  beforeEach(async () => {
    mockCourseService = {
      getCourses: jasmine.createSpy('getCourses').and.returnValue(of([
        { id: 1, name: 'CS Course', code: 'CS101', credits: 4, gradeStatus: 'passed' }
      ]))
    };

    await TestBed.configureTestingModule({
      imports: [Home],
      providers: [
        { provide: CourseService, useValue: mockCourseService }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(Home);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

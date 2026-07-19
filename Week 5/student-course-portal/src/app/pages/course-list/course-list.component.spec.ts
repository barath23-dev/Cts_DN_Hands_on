import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideMockStore, MockStore } from '@ngrx/store/testing';
import { CourseList } from './course-list.component';
import { By } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { Course } from '../../models/course.model';
import { selectAllCourses, selectCoursesLoading, selectCoursesError } from '../../store/course/course.selectors';
import { selectEnrolledIds } from '../../store/enrollment/enrollment.selectors';

describe('CourseList (Store Connected)', () => {
  let component: CourseList;
  let fixture: ComponentFixture<CourseList>;
  let store: MockStore;
  
  const mockCourses: Course[] = [
    { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' },
    { id: 2, name: 'Database Management', code: 'CS202', credits: 3, gradeStatus: 'passed' }
  ];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseList, RouterModule.forRoot([])],
      providers: [
        provideMockStore({
          initialState: {
            course: { courses: mockCourses, loading: false, error: null },
            enrollment: { enrolledCourseIds: [], loading: false, error: null }
          }
        })
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseList);
    component = fixture.componentInstance;
    store = TestBed.inject(MockStore);
    
    store.overrideSelector(selectAllCourses, mockCourses);
    store.overrideSelector(selectCoursesLoading, false);
    store.overrideSelector(selectCoursesError, null);
    store.overrideSelector(selectEnrolledIds, []);
    
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render course cards from store state', () => {
    fixture.detectChanges();
    const cards = fixture.debugElement.queryAll(By.css('app-course-card'));
    expect(cards.length).toBe(2);
  });

  it('should show loading spinner when store loading is true', () => {
    store.overrideSelector(selectCoursesLoading, true);
    store.refreshState();
    fixture.detectChanges();
    const spinner = fixture.debugElement.query(By.css('.spinner'));
    expect(spinner).toBeTruthy();
  });
});

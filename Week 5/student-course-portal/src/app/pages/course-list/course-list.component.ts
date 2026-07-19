import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AsyncPipe } from '@angular/common';
import { Store } from '@ngrx/store';
import { Observable, BehaviorSubject, combineLatest } from 'rxjs';
import { map } from 'rxjs/operators';
import { CourseCard } from '../../components/course-card/course-card.component';
import { CourseSummary } from '../../components/course-summary/course-summary.component';
import { Course } from '../../models/course.model';

import * as CourseActions from '../../store/course/course.actions';
import * as EnrollmentActions from '../../store/enrollment/enrollment.actions';
import { selectAllCourses, selectCoursesLoading, selectCoursesError } from '../../store/course/course.selectors';
import { selectEnrolledIds } from '../../store/enrollment/enrollment.selectors';

@Component({
  selector: 'app-course-list',
  imports: [CourseCard, CourseSummary, FormsModule, AsyncPipe],
  templateUrl: './course-list.component.html',
  styleUrl: './course-list.component.css',
})
export class CourseList implements OnInit {
  courses$: Observable<Course[]>;
  filteredCourses$: Observable<Course[]>;
  isLoading$: Observable<boolean>;
  errorMessage$: Observable<string | null>;
  enrolledIds$: Observable<number[]>;
  
  selectedCourseId: number | null = null;
  searchTerm = '';
  private searchSubject = new BehaviorSubject<string>('');

  constructor(
    private store: Store,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.courses$ = this.store.select(selectAllCourses);
    this.isLoading$ = this.store.select(selectCoursesLoading);
    this.errorMessage$ = this.store.select(selectCoursesError);
    this.enrolledIds$ = this.store.select(selectEnrolledIds);

    this.filteredCourses$ = combineLatest([this.courses$, this.searchSubject]).pipe(
      map(([courses, searchTerm]) => {
        if (!searchTerm) {
          return courses;
        }
        const term = searchTerm.toLowerCase();
        return courses.filter(
          c => c.name.toLowerCase().includes(term) || c.code.toLowerCase().includes(term)
        );
      })
    );
  }

  ngOnInit() {
    this.store.dispatch(CourseActions.loadCourses());
    this.store.dispatch(EnrollmentActions.loadEnrolledCourseIds());

    this.route.queryParamMap.subscribe(params => {
      const searchParam = params.get('search');
      if (searchParam) {
        this.searchTerm = searchParam;
        this.searchSubject.next(this.searchTerm);
      }
    });
  }

  onSearchChange() {
    this.searchSubject.next(this.searchTerm);
    this.router.navigate(['/courses'], {
      queryParams: { search: this.searchTerm || null },
      queryParamsHandling: 'merge'
    });
  }

  onCardClick(courseId: number) {
    this.router.navigate(['/courses', courseId]);
  }

  trackByCourseId(index: number, course: Course): number {
    return course.id;
  }

  onEnroll(courseId: number) {
    this.selectedCourseId = courseId;
    this.enrolledIds$.subscribe(ids => {
      const isEnrolled = ids.includes(courseId);
      if (isEnrolled) {
        this.store.dispatch(EnrollmentActions.unenrollFromCourse({ courseId }));
      } else {
        this.store.dispatch(EnrollmentActions.enrollInCourse({ courseId }));
      }
    }).unsubscribe();
  }
}

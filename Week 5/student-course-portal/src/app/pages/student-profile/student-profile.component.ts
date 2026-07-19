import { Component, OnInit } from '@angular/core';
import { AsyncPipe } from '@angular/common';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { CourseCard } from '../../components/course-card/course-card.component';
import { Notification } from '../../components/notification/notification.component';
import { Course } from '../../models/course.model';

import * as CourseActions from '../../store/course/course.actions';
import * as EnrollmentActions from '../../store/enrollment/enrollment.actions';
import { selectEnrolledCourses } from '../../store/enrollment/enrollment.selectors';

@Component({
  selector: 'app-student-profile',
  imports: [CourseCard, Notification, AsyncPipe],
  templateUrl: './student-profile.component.html',
  styleUrl: './student-profile.component.css',
})
export class StudentProfile implements OnInit {
  student = {
    name: 'Alex Johnson',
    email: 'alex.johnson@university.edu',
    gpa: '3.8',
    major: 'Computer Science & Engineering',
  };
  enrolledCourses$: Observable<Course[]>;

  constructor(private store: Store) {
    this.enrolledCourses$ = this.store.select(selectEnrolledCourses);
  }

  ngOnInit() {
    this.store.dispatch(CourseActions.loadCourses());
    this.store.dispatch(EnrollmentActions.loadEnrolledCourseIds());
  }

  onUnenroll(courseId: number) {
    this.store.dispatch(EnrollmentActions.unenrollFromCourse({ courseId }));
  }
}

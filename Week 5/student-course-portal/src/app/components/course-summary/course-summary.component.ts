import { Component } from '@angular/core';
import { AsyncPipe } from '@angular/common';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { CourseService } from '../../services/course.service';
import { EnrollmentService } from '../../services/enrollment.service';

@Component({
  selector: 'app-course-summary',
  imports: [AsyncPipe],
  templateUrl: './course-summary.component.html',
  styleUrl: './course-summary.component.css',
})
export class CourseSummary {
  totalCourses$: Observable<number>;
  enrolledCoursesCount$: Observable<number>;

  constructor(
    private courseService: CourseService,
    private enrollmentService: EnrollmentService
  ) {
    this.totalCourses$ = this.courseService.getCourses().pipe(map(c => c.length));
    this.enrolledCoursesCount$ = this.enrollmentService.getEnrolledCourses().pipe(map(c => c.length));
  }
}

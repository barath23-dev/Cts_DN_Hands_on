import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin, of } from 'rxjs';
import { map, switchMap, catchError } from 'rxjs/operators';
import { CourseService } from './course.service';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root',
})
export class EnrollmentService {
  private enrollmentsUrl = 'http://localhost:3000/enrollments';

  constructor(
    private http: HttpClient,
    private courseService: CourseService
  ) {}

  isEnrolled(courseId: number): Observable<boolean> {
    return this.http.get<any[]>(`${this.enrollmentsUrl}?courseId=${courseId}`).pipe(
      map(enrollments => enrollments.length > 0),
      catchError(() => of(false))
    );
  }

  enroll(courseId: number): Observable<any> {
    return this.http.post(this.enrollmentsUrl, { courseId });
  }

  unenroll(courseId: number): Observable<any> {
    return this.http.get<any[]>(`${this.enrollmentsUrl}?courseId=${courseId}`).pipe(
      switchMap(enrollments => {
        if (enrollments.length > 0) {
          const enrollmentId = enrollments[0].id;
          return this.http.delete(`${this.enrollmentsUrl}/${enrollmentId}`);
        }
        return of(null);
      }),
      catchError(() => of(null))
    );
  }

  getEnrolledCourses(): Observable<Course[]> {
    return this.http.get<any[]>(this.enrollmentsUrl).pipe(
      switchMap(enrollments => {
        if (enrollments.length === 0) {
          return of([]);
        }
        const requests = enrollments.map(e => this.courseService.getCourseById(Number(e.courseId)));
        return forkJoin(requests);
      }),
      catchError(() => of([]))
    );
  }

  // Showcase switchMap logic as required by step 87
  getStudentsByCourse(courseId: number): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:3000/students?courseId=${courseId}`);
  }
}

import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, switchMap, mergeMap } from 'rxjs/operators';
import { EnrollmentService } from '../../services/enrollment.service';
import * as EnrollmentActions from './enrollment.actions';

@Injectable()
export class EnrollmentEffects {
  loadEnrolledCourseIds$ = createEffect(() =>
    this.actions$.pipe(
      ofType(EnrollmentActions.loadEnrolledCourseIds),
      switchMap(() =>
        this.enrollmentService.getEnrolledCourses().pipe(
          map((courses) =>
            EnrollmentActions.loadEnrolledCourseIdsSuccess({ courseIds: courses.map((c) => c.id) })
          ),
          catchError((error) => of(EnrollmentActions.loadEnrolledCourseIdsFailure({ error: error.message })))
        )
      )
    )
  );

  enrollInCourse$ = createEffect(() =>
    this.actions$.pipe(
      ofType(EnrollmentActions.enrollInCourse),
      mergeMap(({ courseId }) =>
        this.enrollmentService.enroll(courseId).pipe(
          map(() => EnrollmentActions.enrollInCourseSuccess({ courseId })),
          catchError((error) => of(EnrollmentActions.enrollInCourseFailure({ error: error.message })))
        )
      )
    )
  );

  unenrollFromCourse$ = createEffect(() =>
    this.actions$.pipe(
      ofType(EnrollmentActions.unenrollFromCourse),
      mergeMap(({ courseId }) =>
        this.enrollmentService.unenroll(courseId).pipe(
          map(() => EnrollmentActions.unenrollFromCourseSuccess({ courseId })),
          catchError((error) => of(EnrollmentActions.unenrollFromCourseFailure({ error: error.message })))
        )
      )
    )
  );

  constructor(
    private actions$: Actions,
    private enrollmentService: EnrollmentService
  ) {}
}

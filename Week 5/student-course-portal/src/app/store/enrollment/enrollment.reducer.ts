import { createReducer, on } from '@ngrx/store';
import * as EnrollmentActions from './enrollment.actions';

export interface EnrollmentState {
  enrolledCourseIds: number[];
  loading: boolean;
  error: string | null;
}

export const initialState: EnrollmentState = {
  enrolledCourseIds: [],
  loading: false,
  error: null,
};

export const enrollmentReducer = createReducer(
  initialState,
  on(EnrollmentActions.loadEnrolledCourseIds, (state) => ({
    ...state,
    loading: true,
    error: null,
  })),
  on(EnrollmentActions.loadEnrolledCourseIdsSuccess, (state, { courseIds }) => ({
    ...state,
    enrolledCourseIds: courseIds,
    loading: false,
    error: null,
  })),
  on(EnrollmentActions.loadEnrolledCourseIdsFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error,
  })),
  on(EnrollmentActions.enrollInCourseSuccess, (state, { courseId }) => ({
    ...state,
    enrolledCourseIds: state.enrolledCourseIds.includes(courseId)
      ? state.enrolledCourseIds
      : [...state.enrolledCourseIds, courseId],
  })),
  on(EnrollmentActions.unenrollFromCourseSuccess, (state, { courseId }) => ({
    ...state,
    enrolledCourseIds: state.enrolledCourseIds.filter((id) => id !== courseId),
  }))
);

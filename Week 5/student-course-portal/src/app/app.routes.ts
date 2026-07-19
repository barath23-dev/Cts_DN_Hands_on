import { Routes } from '@angular/router';
import { Home } from './pages/home/home.component';
import { CoursesLayout } from './pages/courses-layout/courses-layout.component';
import { CourseList } from './pages/course-list/course-list.component';
import { CourseDetail } from './pages/course-detail/course-detail.component';
import { StudentProfile } from './pages/student-profile/student-profile.component';
import { NotFound } from './pages/not-found/not-found.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', component: Home },
  {
    path: 'courses',
    component: CoursesLayout,
    children: [
      { path: '', component: CourseList },
      { path: ':id', component: CourseDetail }
    ]
  },
  {
    path: 'profile',
    component: StudentProfile,
    canActivate: [authGuard]
  },
  {
    path: 'enroll',
    loadChildren: () => import('./features/enrollment/enrollment.module').then(m => m.EnrollmentModule),
    canActivate: [authGuard]
  },
  { path: '**', component: NotFound }
];

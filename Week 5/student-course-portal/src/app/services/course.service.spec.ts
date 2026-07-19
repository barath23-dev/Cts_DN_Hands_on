import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { CourseService } from './course.service';
import { Course } from '../models/course.model';

describe('CourseService', () => {
  let service: CourseService;
  let httpMock: HttpTestingController;

  const mockCourses: Course[] = [
    { id: 1, name: 'Course 1', code: 'C1', credits: 3, gradeStatus: 'passed' },
    { id: 2, name: 'Course 2', code: 'C2', credits: 4, gradeStatus: 'passed' },
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        CourseService,
        provideHttpClient(),
        provideHttpClientTesting(),
      ],
    });
    service = TestBed.inject(CourseService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch all courses with getCourses()', () => {
    service.getCourses().subscribe((courses) => {
      expect(courses.length).toBe(2);
      expect(courses).toEqual(mockCourses);
    });

    const req = httpMock.expectOne('http://localhost:3000/courses');
    expect(req.request.method).toBe('GET');
    req.flush(mockCourses);
  });

  it('should handle error when API call fails with retry(2) strategy', () => {
    service.getCourses().subscribe({
      next: () => fail('should have failed with 500 error'),
      error: (error) => {
        expect(error.message).toContain('Failed to load courses');
      },
    });

    // Initial request
    const req1 = httpMock.expectOne('http://localhost:3000/courses');
    req1.flush('Error loading courses', { status: 500, statusText: 'Internal Server Error' });

    // First retry
    const req2 = httpMock.expectOne('http://localhost:3000/courses');
    req2.flush('Error loading courses', { status: 500, statusText: 'Internal Server Error' });

    // Second retry
    const req3 = httpMock.expectOne('http://localhost:3000/courses');
    req3.flush('Error loading courses', { status: 500, statusText: 'Internal Server Error' });
  });
});

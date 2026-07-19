import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CourseService } from '../../services/course.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-home',
  imports: [FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class Home implements OnInit, OnDestroy {
  portalName = 'Student Course Portal';
  isPortalActive = true;
  message = '';
  searchTerm = '';
  coursesCount = 0;
  private courseSub?: Subscription;

  constructor(private courseService: CourseService) {}

  onEnrollClick() {
    this.message = 'Enrollment opened!';
  }

  ngOnInit() {
    this.courseSub = this.courseService.getCourses().subscribe({
      next: (courses) => {
        this.coursesCount = courses.length;
      },
      error: (err) => console.error(err)
    });
    console.log('HomeComponent initialised — courses loaded');
  }

  ngOnDestroy() {
    if (this.courseSub) {
      this.courseSub.unsubscribe();
    }
    console.log('HomeComponent destroyed');
  }
}

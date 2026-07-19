import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CourseService } from '../../services/course.service';
import { Course } from '../../models/course.model';

@Component({
  selector: 'app-course-detail',
  imports: [RouterLink],
  templateUrl: './course-detail.component.html',
  styleUrl: './course-detail.component.css',
})
export class CourseDetail implements OnInit {
  course: Course | undefined;

  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService
  ) {}

  ngOnInit() {
    const idStr = this.route.snapshot.paramMap.get('id');
    if (idStr) {
      const id = parseInt(idStr, 10);
      this.courseService.getCourseById(id).subscribe({
        next: (c) => this.course = c,
        error: (err) => console.error(err)
      });
    }
  }
}

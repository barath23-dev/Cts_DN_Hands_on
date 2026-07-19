import { Component, Input, Output, EventEmitter, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { NgClass, NgStyle } from '@angular/common';
import { Highlight } from '../../directives/highlight.directive';
import { CreditLabel } from '../../pipes/credit-label.pipe';
import { EnrollmentService } from '../../services/enrollment.service';

@Component({
  selector: 'app-course-card',
  imports: [NgClass, NgStyle, Highlight, CreditLabel],
  templateUrl: './course-card.component.html',
  styleUrl: './course-card.component.css',
})
export class CourseCard implements OnInit, OnChanges {
  @Input() course!: { id: number; name: string; code: string; credits: number; gradeStatus?: string };
  @Input() isEnrolled = false;
  @Output() enrollRequested = new EventEmitter<number>();

  isExpanded = false;
  enrolledStatus = false;

  constructor(private enrollmentService: EnrollmentService) {}

  ngOnInit() {
    this.checkEnrollment();
  }

  checkEnrollment() {
    this.enrollmentService.isEnrolled(this.course.id).subscribe(val => {
      this.enrolledStatus = val || this.isEnrolled;
    });
  }

  get cardClasses() {
    return {
      'card--enrolled': this.enrolledStatus,
      'card--full': this.course.credits >= 4,
      'expanded': this.isExpanded,
    };
  }

  toggleExpand(event: MouseEvent) {
    event.stopPropagation();
    this.isExpanded = !this.isExpanded;
  }

  onEnrollClick(event: MouseEvent) {
    event.stopPropagation();
    if (this.enrolledStatus) {
      this.enrollmentService.unenroll(this.course.id).subscribe(() => {
        this.enrolledStatus = false;
        this.enrollRequested.emit(this.course.id);
      });
    } else {
      this.enrollmentService.enroll(this.course.id).subscribe(() => {
        this.enrolledStatus = true;
        this.enrollRequested.emit(this.course.id);
      });
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['course']) {
      this.checkEnrollment();
      console.log('Course changed:', changes['course'].previousValue, '->', changes['course'].currentValue);
    }
    if (changes['isEnrolled']) {
      this.enrolledStatus = this.isEnrolled;
    }
  }
}

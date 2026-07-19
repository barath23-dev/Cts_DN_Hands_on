import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-enrollment-form',
  imports: [FormsModule],
  templateUrl: './enrollment-form.component.html',
  styleUrl: './enrollment-form.component.css',
})
export class EnrollmentForm {
  studentName = '';
  studentEmail = '';
  courseId: number | null = null;
  preferredSemester = 'Odd';
  agreeToTerms = false;
  submitted = false;

  onSubmit(form: NgForm) {
    if (form.valid) {
      console.log('Form value:', form.value);
      console.log('Form valid:', form.valid);
      this.submitted = true;
    }
  }
}

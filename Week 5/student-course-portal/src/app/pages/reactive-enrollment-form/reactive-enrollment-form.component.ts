import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, FormControl, Validators, AbstractControl, ValidationErrors, ReactiveFormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';
import { CanComponentDeactivate } from '../../guards/unsaved-changes.guard';

function noCourseCode(control: AbstractControl): ValidationErrors | null {
  const val = control.value;
  if (val && typeof val === 'string' && val.toUpperCase().startsWith('XX')) {
    return { noCourseCode: true };
  }
  return null;
}

function simulateEmailCheck(control: AbstractControl): Promise<ValidationErrors | null> {
  return new Promise((resolve) => {
    setTimeout(() => {
      const email = control.value;
      if (email && typeof email === 'string' && email.includes('test@')) {
        resolve({ emailTaken: true });
      } else {
        resolve(null);
      }
    }, 800);
  });
}

@Component({
  selector: 'app-reactive-enrollment-form',
  imports: [ReactiveFormsModule, NgFor],
  templateUrl: './reactive-enrollment-form.component.html',
  styleUrl: './reactive-enrollment-form.component.css',
})
export class ReactiveEnrollmentForm implements OnInit, CanComponentDeactivate {
  enrollForm!: FormGroup;
  submitted = false;

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.enrollForm = this.fb.group({
      studentName: ['', [Validators.required, Validators.minLength(3)]],
      studentEmail: ['', [Validators.required, Validators.email], [simulateEmailCheck]],
      courseId: ['', [Validators.required, noCourseCode]],
      preferredSemester: ['Odd', Validators.required],
      agreeToTerms: [false, Validators.requiredTrue],
      additionalCourses: this.fb.array([]),
    });
  }

  get additionalCourses() {
    return this.enrollForm.get('additionalCourses') as FormArray;
  }

  getAsFormControl(control: AbstractControl): FormControl {
    return control as FormControl;
  }

  addCourse() {
    this.additionalCourses.push(new FormControl('', Validators.required));
  }

  removeCourse(index: number) {
    this.additionalCourses.removeAt(index);
  }

  canDeactivate(): boolean {
    if (this.enrollForm && this.enrollForm.dirty && !this.submitted) {
      return window.confirm('You have unsaved changes. Leave?');
    }
    return true;
  }

  onSubmit() {
    if (this.enrollForm.valid) {
      console.log('Form Value (excluding disabled):', this.enrollForm.value);
      console.log('Form Raw Value (including disabled):', this.enrollForm.getRawValue());
      this.submitted = true;
    }
  }
}

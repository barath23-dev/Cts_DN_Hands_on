import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../../services/notification.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-notification',
  imports: [NgFor],
  providers: [NotificationService],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css',
})
export class Notification implements OnInit {
  constructor(private notificationService: NotificationService) {}

  ngOnInit() {
    this.notificationService.addNotification('Welcome to the Student Course Portal!');
    this.notificationService.addNotification('Make sure to check your grade statuses in the Courses tab.');
  }

  get notifications(): string[] {
    return this.notificationService.getNotifications();
  }

  addTestNotification() {
    this.notificationService.addNotification('System Alert: Update received at ' + new Date().toLocaleTimeString());
  }
}

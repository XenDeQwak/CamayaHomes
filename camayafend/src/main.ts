import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { CustomerComponent } from './app/customer.component';

bootstrapApplication(CustomerComponent, appConfig)
  .catch(err => console.error(err));

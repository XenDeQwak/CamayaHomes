import { BootstrapContext, bootstrapApplication } from '@angular/platform-browser';
import { CustomerComponent } from './app/customer.component';
import { config } from './app/app.config.server';

const bootstrap = (context: BootstrapContext) =>
    bootstrapApplication(CustomerComponent, config, context);

export default bootstrap;

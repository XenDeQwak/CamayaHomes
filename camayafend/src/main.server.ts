import { BootstrapContext, bootstrapApplication } from '@angular/platform-browser';
import { LoginComponent } from './app/components/login.component';
import { config } from './app/app.config.server';

const bootstrap = (context: BootstrapContext) =>
    bootstrapApplication(LoginComponent, config, context);

export default bootstrap;

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';

import { CourseListComponent } from './course-list/course-list.component';
import { CourseUpdateComponent } from './course-update/course-update.component';
import { CourseCreateComponent } from './course-create/course-create.component';
import { ApplicantCreateComponent } from './applicant-create/applicant-create.component';
import { ApplicantListComponent } from './applicant-list/applicant-list.component';
import { ApplicantUpdateComponent } from './applicant-update/applicant-update.component';
import { HomeComponent } from './home/home.component';
import { AboutusComponent } from './aboutus/aboutus.component';

import { UniversityStaffCreateComponent } from './university-staff-create/university-staff-create.component';
import { UniversityStaffUpdateComponent } from './university-staff-update/university-staff-update.component';
import { UniversityStaffListComponent } from './university-staff-list/university-staff-list.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AdmissionCommiteeMemberCreateComponent } from './admission-commitee-member-create/admission-commitee-member-create.component';
import { AdmissionCommiteeMemberListComponent } from './admission-commitee-member-list/admission-commitee-member-list.component';
import { AdmissionCommiteeMemberUpdateComponent } from './admission-commitee-member-update/admission-commitee-member-update.component';
import { AdmissionCreateComponent } from './admission-create/admission-create.component';
import { AdmissionListComponent } from './admission-list/admission-list.component';
import { AdmissionUpdateComponent } from './admission-update/admission-update.component';
import { ProfileComponent } from './profile/profile.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { AdmissionCommiteeMemberStatusComponent } from './admission-commitee-member-status/admission-commitee-member-status.component';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { Ng2OrderModule } from 'ng2-order-pipe';
import { NgxPaginationModule } from 'ngx-pagination';
import { AngularFontAwesomeModule } from 'angular-font-awesome';

import { CourseDeleteComponent } from './course-delete/course-delete.component';
import { AdmissionDeleteComponent } from './admission-delete/admission-delete.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component'



@NgModule({
  declarations: [
    AppComponent,
    CourseListComponent,
    CourseUpdateComponent,
    CourseCreateComponent,
    ApplicantCreateComponent,
    ApplicantListComponent,
    ApplicantUpdateComponent,
    HomeComponent,
    AboutusComponent,
    UniversityStaffCreateComponent,
    UniversityStaffUpdateComponent,
    UniversityStaffListComponent,
    LoginComponent,
    LogoutComponent,
    AdmissionCommiteeMemberCreateComponent,
    AdmissionCommiteeMemberListComponent,
    AdmissionCommiteeMemberUpdateComponent,
    AdmissionCreateComponent,
    AdmissionListComponent,
    AdmissionUpdateComponent,
    ProfileComponent,
    ChangePasswordComponent,
    AdmissionCommiteeMemberStatusComponent,
    CourseDeleteComponent,
    AdmissionDeleteComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    Ng2OrderModule,
    NgxPaginationModule,
    AngularFontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

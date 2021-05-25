import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AboutusComponent } from './aboutus/aboutus.component';
import { ApplicantCreateComponent } from './applicant-create/applicant-create.component';
import { ApplicantListComponent } from './applicant-list/applicant-list.component';
import { ApplicantUpdateComponent } from './applicant-update/applicant-update.component';
import { HomeComponent } from './home/home.component';
import { UniversityStaffCreateComponent } from './university-staff-create/university-staff-create.component';
import { UniversityStaffListComponent } from './university-staff-list/university-staff-list.component';
import { UniversityStaffUpdateComponent } from './university-staff-update/university-staff-update.component';

import { AdmissionCommiteeMemberCreateComponent } from './admission-commitee-member-create/admission-commitee-member-create.component';
import { AdmissionCommiteeMemberListComponent } from './admission-commitee-member-list/admission-commitee-member-list.component';
import { AdmissionCommiteeMemberUpdateComponent } from './admission-commitee-member-update/admission-commitee-member-update.component';

import { AuthguardService } from './services/authguard.service';


import { LoginComponent } from './login/login.component'
import { LogoutComponent } from './logout/logout.component'
import { CourseListComponent } from './course-list/course-list.component';
import { CourseCreateComponent } from './course-create/course-create.component';
import { CourseUpdateComponent } from './course-update/course-update.component';
import { ProfileComponent } from './profile/profile.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { AdmissionListComponent } from './admission-list/admission-list.component';
import { AdmissionCreateComponent } from './admission-create/admission-create.component';
import { AdmissionUpdateComponent } from './admission-update/admission-update.component';
import { AdmissionCommiteeMemberStatusComponent } from './admission-commitee-member-status/admission-commitee-member-status.component';
import { CourseDeleteComponent } from './course-delete/course-delete.component';
import { AdmissionDeleteComponent } from './admission-delete/admission-delete.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AppComponent } from './app.component';

const routes: Routes = [

  { path: '',   redirectTo: '/home', pathMatch: 'full' },
  
  { path: 'staff-create', component: UniversityStaffCreateComponent, canActivate: [AuthguardService] },
  { path: 'staff-list', component: UniversityStaffListComponent, canActivate: [AuthguardService] },
  { path: 'staff-update/:staffId', component: UniversityStaffUpdateComponent, canActivate: [AuthguardService] },

  //{ path: 'staff-update', component: UniversityStaffUpdateComponent, canActivate: [AuthguardService] },

  { path: 'admission-commitee-member-create', component: AdmissionCommiteeMemberCreateComponent, canActivate: [AuthguardService] },
  { path: 'admission-commitee-member-list', component: AdmissionCommiteeMemberListComponent, canActivate: [AuthguardService] },
  { path: 'admission-commitee-member-status/:admissionId', component: AdmissionCommiteeMemberStatusComponent, canActivate: [AuthguardService] },
  { path: 'admission-commitee-member-update/:admissionCommiteeMemberId', component: AdmissionCommiteeMemberUpdateComponent, canActivate: [AuthguardService] },


  { path: 'home', component: HomeComponent },
  { path: 'aboutus', component: AboutusComponent },
  { path: 'applicant-create', component: ApplicantCreateComponent},
  { path: 'applicant-list', component: ApplicantListComponent ,canActivate:[AuthguardService]},
  { path: 'applicant-update/:applicantId', component: ApplicantUpdateComponent,canActivate:[AuthguardService] },



  { path: 'course-list', component: CourseListComponent },
  { path: 'course-create', component: CourseCreateComponent, canActivate: [AuthguardService] },

  { path: 'course-update/:courseId', component: CourseUpdateComponent, canActivate: [AuthguardService] },
  { path: 'course-delete/:courseId', component: CourseDeleteComponent, canActivate: [AuthguardService] },


  { path: 'profile', component: ProfileComponent },
  { path: 'login', component: LoginComponent },
  { path: 'changePassword', component: ChangePasswordComponent },
  { path: 'logout', component: LogoutComponent },

  { path: 'admission-create', component: AdmissionCreateComponent ,canActivate: [AuthguardService]},
  { path: 'admission-list', component: AdmissionListComponent },
  { path: 'admission-delete/:admissionId', component: AdmissionDeleteComponent,canActivate: [AuthguardService] },
  { path: 'admission-update/:admissionId', component: AdmissionUpdateComponent,canActivate: [AuthguardService] },

  { path: '**', component: PageNotFoundComponent },  // Wildcard route for a 404 page
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

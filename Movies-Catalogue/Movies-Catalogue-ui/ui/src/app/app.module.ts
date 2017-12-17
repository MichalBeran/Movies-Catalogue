import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {GenreComponent} from './catalogue/genre/genre.component';
import {CatalogueComponent} from './catalogue/catalogue.component';
import { AppRoutingModule } from './/app-routing.module';
import { TemplateComponent } from './template/template.component';
import { HeaderComponent } from './template/header/header.component';
import { FooterComponent } from './template/footer/footer.component';
import { MovieComponent } from './catalogue/movie/movie.component';
import { RatingComponent } from './catalogue/rating/rating.component';
import { DirectorComponent } from './catalogue/director/director.component';
import { ActorComponent } from './catalogue/actor/actor.component';
import { GenreCreateComponent } from './catalogue/genre/genre-create/genre-create.component';
import { GenreDetailComponent } from './catalogue/genre/genre-detail/genre-detail.component';
import { GenreIndexComponent } from './catalogue/genre/genre-index/genre-index.component';
import { MovieIndexComponent } from './catalogue/movie/movie-index/movie-index.component';
import { MovieDetailComponent } from './catalogue/movie/movie-detail/movie-detail.component';
import { MovieCreateComponent } from './catalogue/movie/movie-create/movie-create.component';
import { RatingIndexComponent } from './catalogue/rating/rating-index/rating-index.component';
import { RatingCreateComponent } from './catalogue/rating/rating-create/rating-create.component';
import { RatingDetailComponent } from './catalogue/rating/rating-detail/rating-detail.component';
import { DirectorIndexComponent } from './catalogue/director/director-index/director-index.component';
import { DirectorCreateComponent } from './catalogue/director/director-create/director-create.component';
import { DirectorDetailComponent } from './catalogue/director/director-detail/director-detail.component';
import { ActorIndexComponent } from './catalogue/actor/actor-index/actor-index.component';
import { ActorCreateComponent } from './catalogue/actor/actor-create/actor-create.component';
import { ActorDetailComponent } from './catalogue/actor/actor-detail/actor-detail.component';
import { SidenavComponent } from './template/sidenav/sidenav.component';
import {RestService} from './services/rest.service';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {GenreCommonComponent} from './catalogue/genre/genre.common.component';
import {UserComponent} from './catalogue/user/user.component';
import {UserIndexComponent} from './catalogue/user/user-index/user-index.component';
import {UserRegisterComponent} from './catalogue/user/user-register/user-register.component';
import {UserCommonComponent} from './catalogue/user/user.common.component';
import {UserLoginComponent} from './catalogue/user/user-login/user-login.component';
import {UserDetailComponent} from './catalogue/user/user-detail/user-detail.component';
import {AppHelper} from "./app.helper";
import {UserService} from "./services/user.service";
import {RatingCommonComponent} from "./catalogue/rating/rating.common.component";
import {DirectorCommonComponent} from "./catalogue/director/director.common.component";


@NgModule({
  declarations: [
    AppComponent,
    CatalogueComponent,
    GenreComponent,
    TemplateComponent,
    HeaderComponent,
    FooterComponent,
    MovieComponent,
    RatingComponent,
    DirectorComponent,
    ActorComponent,
    GenreCommonComponent,
    GenreCreateComponent,
    GenreDetailComponent,
    GenreIndexComponent,
    MovieIndexComponent,
    MovieDetailComponent,
    MovieCreateComponent,
    RatingCommonComponent,
    RatingIndexComponent,
    RatingCreateComponent,
    RatingDetailComponent,
    DirectorCommonComponent,
    DirectorIndexComponent,
    DirectorCreateComponent,
    DirectorDetailComponent,
    ActorIndexComponent,
    ActorCreateComponent,
    ActorDetailComponent,
    SidenavComponent,
    UserComponent,
    UserIndexComponent,
    UserRegisterComponent,
    UserCommonComponent,
    UserLoginComponent,
    UserDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [RestService, AppHelper, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }

import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CatalogueComponent} from './catalogue/catalogue.component';
import {GenreComponent} from './catalogue/genre/genre.component';
import {MovieComponent} from './catalogue/movie/movie.component';
import {ActorComponent} from './catalogue/actor/actor.component';
import {DirectorComponent} from './catalogue/director/director.component';
import {RatingComponent} from './catalogue/rating/rating.component';
import {GenreCreateComponent} from './catalogue/genre/genre-create/genre-create.component';
import {GenreDetailComponent} from './catalogue/genre/genre-detail/genre-detail.component';
import {GenreIndexComponent} from './catalogue/genre/genre-index/genre-index.component';
import {MovieIndexComponent} from './catalogue/movie/movie-index/movie-index.component';
import {MovieCreateComponent} from './catalogue/movie/movie-create/movie-create.component';
import {MovieDetailComponent} from './catalogue/movie/movie-detail/movie-detail.component';
import {ActorIndexComponent} from './catalogue/actor/actor-index/actor-index.component';
import {ActorCreateComponent} from './catalogue/actor/actor-create/actor-create.component';
import {ActorDetailComponent} from './catalogue/actor/actor-detail/actor-detail.component';
import {DirectorIndexComponent} from './catalogue/director/director-index/director-index.component';
import {DirectorCreateComponent} from './catalogue/director/director-create/director-create.component';
import {DirectorDetailComponent} from './catalogue/director/director-detail/director-detail.component';
import {RatingIndexComponent} from './catalogue/rating/rating-index/rating-index.component';
import {RatingCreateComponent} from './catalogue/rating/rating-create/rating-create.component';
import {RatingDetailComponent} from './catalogue/rating/rating-detail/rating-detail.component';
import {UserComponent} from './catalogue/user/user.component';
import {UserIndexComponent} from './catalogue/user/user-index/user-index.component';
import {UserRegisterComponent} from './catalogue/user/user-register/user-register.component';

/**
 * @author Marek Urban
 */
const routes: Routes = [
  {
    path: '',
    component: CatalogueComponent,
    children: [
      {
        path: 'genres',
        component: GenreComponent,
        children: [
          {
            path: '',
            pathMatch: 'full',
            redirectTo: 'index'
          },
          {
            path: 'index',
            component: GenreIndexComponent
          },
          {
            path: 'create',
            component: GenreCreateComponent
          },
          {
            path: 'detail/:id',
            component: GenreDetailComponent
          },
          {
            path: 'edit/:id',
            component: GenreCreateComponent
          }
        ]
      },
      {
        path: 'movies',
        component: MovieComponent,
        children: [
          {
            path: '',
            pathMatch: 'full',
            redirectTo: 'index'
          },
          {
            path: 'index',
            component: MovieIndexComponent
          },
          {
            path: 'create',
            component: MovieCreateComponent
          },
          {
            path: 'detail',
            component: MovieDetailComponent
          }
        ]
      },
      {
        path: 'actors',
        component: ActorComponent,
        children: [
          {
            path: '',
            pathMatch: 'full',
            redirectTo: 'index'
          },
          {
            path: 'index',
            component: ActorIndexComponent
          },
          {
            path: 'create',
            component: ActorCreateComponent
          },
          {
            path: 'detail',
            component: ActorDetailComponent
          }
        ]
      },
      {
        path: 'directors',
        component: DirectorComponent,
        children: [
          {
            path: '',
            pathMatch: 'full',
            redirectTo: 'index'
          },
          {
            path: 'index',
            component: DirectorIndexComponent
          },
          {
            path: 'create',
            component: DirectorCreateComponent
          },
          {
            path: 'detail',
            component: DirectorDetailComponent
          }
        ]
      },
      {
        path: 'users',
        component: UserComponent,
        children: [
          {
            path: '',
            pathMatch: 'full',
            redirectTo: 'index'
          },
          {
            path: 'index',
            component: UserIndexComponent
          },
          {
            path: 'create',
            component: UserRegisterComponent
          }
        ]
      },
      {
        path: 'ratings',
        component: RatingComponent,
        children: [
          {
            path: '',
            pathMatch: 'full',
            redirectTo: 'index'
          },
          {
            path: 'index',
            component: RatingIndexComponent
          },
          {
            path: 'create',
            component: RatingCreateComponent
          },
          {
            path: 'detail',
            component: RatingDetailComponent
          }
        ]
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

import {AfterViewInit, Component, OnInit} from '@angular/core';
import {MovieCommonComponent} from '../movie.common.component';
import {RestService} from '../../../services/rest.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Movie} from '../../../models/movie.model';
import {isNullOrUndefined} from 'util';
import {MovieCreate} from '../../../models/movie.create.model';
import {Director} from '../../../models/director.model';
import {Genre} from '../../../models/genre.model';
import {Actor} from '../../../models/actor.model';

declare const $: any;
declare const select2: any;

@Component({
  selector: 'app-movie-create',
  templateUrl: './movie-create.component.html',
  styleUrls: ['./movie-create.component.css']
})
export class MovieCreateComponent extends MovieCommonComponent implements OnInit {
  movie: MovieCreate = new MovieCreate();
  file;

  actorsSel: number[] = [];
  genresSel: number[] = [];

  title = 'Create';
  editing = false;
  directors: Director[];
  actors: Actor[];
  genres: Genre[];

  constructor(protected service: RestService, protected router: Router, private route: ActivatedRoute) {
    super(service, router);
  }

  ngOnInit() {

    this.route.params.subscribe(params => {
      if (!isNullOrUndefined(params['id'])) {
        this.service.find(params['id']).subscribe(res => this.setFormForEdit(res));
      } else {
        this.callOthers();
      }

    });
  }

  callOthers() {
    this.service.setRepository('directors');
    this.service.get().subscribe(directors => this.activateDirectors(directors));
    this.service.setRepository('actors');
    this.service.get().subscribe(actors => this.activateActors(actors));
    this.service.setRepository('genres');
    this.service.get().subscribe(genres => this.activateGenres(genres));


  }

  changeListener($event): void {
    this.readThis($event.target);
  }

  readThis(inputValue: any): void {
    const file: File = inputValue.files[0];
    const myReader: FileReader = new FileReader();

    myReader.onloadend = (e) => {
      this.movie.image = myReader.result;
    };
    myReader.readAsDataURL(file);
  }

  save() {
    this.service.setRepository('movies');
    if (isNullOrUndefined(this.movie.id)) {
      // create
      this.movie.actors = [];
      this.actorsSel = $('#s2actrs').select2('data');
      this.actorsSel.forEach((selection: any) => {
        const a = new Actor();
        a.id = selection.id;
        this.movie.actors.push(a);
      });
      this.movie.genres = [];
      this.genresSel = $('#s2actrs').select2('data');
      console.log(this.genresSel);
      this.genresSel.forEach((selection: any) => {
        const g = new Genre();
        g.id = selection.id;
        this.movie.genres.push(g);
      });

      this.service.create(this.movie).subscribe(result => this.redirect(result));
    } else {
      // update
      this.movie.dateOfRelease = null;
      this.movie.actors = [];
      this.actorsSel = $('#s2actrs').select2('data');
      this.actorsSel.forEach((selection: any) => {
        const a = new Actor();
        a.id = selection.id;
        this.movie.actors.push(a);
      });
      this.movie.genres = [];
      this.genresSel = $('#s2actrs').select2('data');
      console.log(this.genresSel);
      this.genresSel.forEach((selection: any) => {
        const g = new Genre();
        g.id = selection.id;
        this.movie.genres.push(g);
      });
      this.service.update(this.movie).subscribe(result => this.redirect(result));
    }
  }

  redirect(created: Movie) {
    this.router.navigateByUrl(`movies/detail/${created.id}`);
  }

  remove(id) {
    super.delete(id, () => super.toIndexPage());
  }

  private setFormForEdit(movie: any) {
    this.actorsSel = [];
    movie.actors.map(actor => this.actorsSel.push(actor.id));
    this.genresSel = [];
    movie.genres.map(genre => this.genresSel.push(genre.id));

    this.title = `Update`;
    this.movie = movie;
    this.editing = true;

    this.callOthers();
  }

  activateDirectors(directors: Director[]) {
    this.directors = directors;
  }

  selectedActors(id: number): boolean {
    if (id in this.actorsSel) {
      return true;
    }
    return false;
  }

  selectedGenres(id: number): boolean {
    if (id in this.genresSel) {
      return true;
    }
    return false;
  }

  private activateActors(actors: any[]) {
    const actorList = [];
    actors.map(actor => actorList.push({id: actor.id, text: actor.firstName + ' ' + actor.lastName}));

    $('#s2actrs').select2({
      data: actorList
    });
    $('#s2actrs').val(this.actorsSel);
    $('#s2actrs').trigger('change');

  }

  activateGenres(genres: any[]) {
    const genresList = [];
    genres.map(genre => genresList.push({id: genre.id, text: genre.name}));
    $('#s2gnrs').select2({
      data: genresList
    });
    $('#s2gnrs').val(this.genresSel);
    $('#s2gnrs').trigger('change');
  }
}


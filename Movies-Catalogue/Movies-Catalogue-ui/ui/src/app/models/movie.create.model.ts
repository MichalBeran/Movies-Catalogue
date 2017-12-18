import {BaseModel} from './base.model';
import {Genre} from "./genre.model";
import {Actor} from "./actor.model";
import {Director} from "./director.model";

export class MovieCreate extends BaseModel {
  title: string;
  description: string;
  image: string;
  date: string;
  genres: Genre[];
  actors: Actor[];
  director: Director;

  constructor() {
    super();
    this.title = '';
    this.description = '';
    this.image = '';
    this.date = '';
    this.actors = [new Actor()];
    this.genres = [new Genre()];
    this.director = new Director;
  }
}

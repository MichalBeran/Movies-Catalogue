import {BaseModel} from './base.model';

export class MovieCreate extends BaseModel {
  title: string;
  description: string;
  image: string;
  date: string;
  genres: any;
  actors: any;
  director: any;
  constructor() {
    super();
    this.title = '';
    this.description = '';
    this.image = '';
    this.date = '';
  }
}

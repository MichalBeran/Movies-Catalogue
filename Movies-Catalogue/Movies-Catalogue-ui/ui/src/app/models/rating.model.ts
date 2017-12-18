import {BaseModel} from './base.model';
import {User} from './user.model';
import {Movie} from './movie.model';

export class Rating extends BaseModel {
  id?: number;
  name: string;
  description: string;
  overallRating: number;
  scenarioRating: number;
  actorsRating: number;
  user: any;
  movie: any;

  constructor() {
    super();
    this.user = new User();
    this.movie = new User();
  }
}

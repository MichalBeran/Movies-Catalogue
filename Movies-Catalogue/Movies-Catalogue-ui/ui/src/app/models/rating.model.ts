import {BaseModel} from './base.model';
import {User} from './user.model';

export class Rating extends BaseModel {
  id?: number;
  name: string;
  description: string;
  overallRating: number;
  scenarioRating: number;
  actorsRating: number;
  user: User;
}

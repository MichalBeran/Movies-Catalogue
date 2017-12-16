import {BaseModel} from './base.model';

export class Authenticated extends BaseModel {
  id: number;
  token: string;
}

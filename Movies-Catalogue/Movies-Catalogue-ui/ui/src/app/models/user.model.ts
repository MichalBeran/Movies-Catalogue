import {BaseModel} from './base.model';

export class User extends BaseModel {
  id?: number;
  nick: string;
  mail: string;
  firstName: string;
  lastName: string;
  password: string;

}

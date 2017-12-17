import {BaseModel} from './base.model';

export class Actor extends BaseModel {
  id?: number;
  firstName: string;
  lastName: string;
  dateOfBirth: string;

}

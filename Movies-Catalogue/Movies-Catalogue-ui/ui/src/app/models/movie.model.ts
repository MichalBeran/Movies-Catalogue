import {BaseModel} from './base.model';

export class Movie extends BaseModel {
  id?: number;
  title: string;
  description: string;
  image: string;
  date: string;
  dateOfRelease: {
    year: string,
    monthValue: string,
    dayOfMonth: string
  };

  constructor() {
    super();
    this.title = '';
    this.description = '';
    this.image = '';
    this.date = '';
    this.dateOfRelease = {
      year: '',
      monthValue: '',
      dayOfMonth: ''
    };
  }
}

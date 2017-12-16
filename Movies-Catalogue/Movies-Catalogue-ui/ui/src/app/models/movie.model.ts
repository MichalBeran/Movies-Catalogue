import {BaseModel} from './base.model';

export class Movie extends BaseModel {
id?: number;
title: string;
description: string;
image: string;
dateOfRelease: string;

}

export interface IBook {
  id?: number;
  title?: string;
  description?: string;
  first50Paragraphs?: string;
}

export class Book implements IBook {
  constructor(public id?: number, public title?: string, public description?: string, public first50Paragraphs?: string) {}
}

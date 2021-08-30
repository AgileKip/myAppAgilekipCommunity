import { IPublisher } from '@/shared/model/publisher.model';
import { IAuthor } from '@/shared/model/author.model';

export interface IBook {
  id?: number;
  title?: string | null;
  pubYear?: string | null;
  price?: number | null;
  publisher?: IPublisher | null;
  authors?: IAuthor[] | null;
}

export class Book implements IBook {
  constructor(
    public id?: number,
    public title?: string | null,
    public pubYear?: string | null,
    public price?: number | null,
    public publisher?: IPublisher | null,
    public authors?: IAuthor[] | null
  ) {}
}

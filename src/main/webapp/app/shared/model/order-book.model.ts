import { IOrder } from '@/shared/model/order.model';
import { IBook } from '@/shared/model/book.model';

export interface IOrderBook {
  id?: number;
  quantity?: number;
  order?: IOrder | null;
  book?: IBook | null;
}

export class OrderBook implements IOrderBook {
  constructor(public id?: number, public quantity?: number, public order?: IOrder | null, public book?: IBook | null) {}
}

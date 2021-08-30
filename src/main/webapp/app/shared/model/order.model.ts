import { IUser } from '@/shared/model/user.model';
import { IStore } from '@/shared/model/store.model';
import { IOrderBook } from '@/shared/model/order-book.model';

import { DeliveryMethod } from '@/shared/model/enumerations/delivery-method.model';
export interface IOrder {
  id?: number;
  number?: string;
  streetAddress?: string | null;
  postalCode?: string | null;
  city?: string | null;
  stateProvince?: string | null;
  deliveryMethod?: DeliveryMethod | null;
  ccNumber?: string | null;
  ccDate?: string | null;
  ccVerifierDigit?: string | null;
  total?: number | null;
  user?: IUser | null;
  store?: IStore | null;
  orderBooks?: IOrderBook[] | null;
}

export class Order implements IOrder {
  constructor(
    public id?: number,
    public number?: string,
    public streetAddress?: string | null,
    public postalCode?: string | null,
    public city?: string | null,
    public stateProvince?: string | null,
    public deliveryMethod?: DeliveryMethod | null,
    public ccNumber?: string | null,
    public ccDate?: string | null,
    public ccVerifierDigit?: string | null,
    public total?: number | null,
    public user?: IUser | null,
    public store?: IStore | null,
    public orderBooks?: IOrderBook[] | null
  ) {}
}

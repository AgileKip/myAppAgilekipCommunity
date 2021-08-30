import { IProcessInstance } from '@/shared/model/process-instance.model';
import { IOrder } from '@/shared/model/order.model';

export interface IOrderBookProcess {
  id?: number;
  processInstance?: IProcessInstance | null;
  order?: IOrder | null;
}

export class OrderBookProcess implements IOrderBookProcess {
  constructor(public id?: number, public processInstance?: IProcessInstance | null, public order?: IOrder | null) {}
}

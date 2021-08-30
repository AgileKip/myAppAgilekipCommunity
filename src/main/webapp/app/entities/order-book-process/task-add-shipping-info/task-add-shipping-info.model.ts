import { ITaskInstance } from '@/shared/model/task-instance.model';
import { IOrderBookProcess } from '@/shared/model/order-book-process.model';

export class TaskAddShippingInfoContext {
  taskInstance?: ITaskInstance = {};
  orderBookProcess?: IOrderBookProcess = {};
}

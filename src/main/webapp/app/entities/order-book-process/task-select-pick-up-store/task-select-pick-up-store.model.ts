import { ITaskInstance } from '@/shared/model/task-instance.model';
import { IOrderBookProcess } from '@/shared/model/order-book-process.model';

export class TaskSelectPickUpStoreContext {
  taskInstance?: ITaskInstance = {};
  orderBookProcess?: IOrderBookProcess = {};
}

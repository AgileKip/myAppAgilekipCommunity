import { ITaskInstance } from '@/shared/model/task-instance.model';
import { IOrderBookProcess } from '@/shared/model/order-book-process.model';

export class TaskPayBookContext {
  taskInstance?: ITaskInstance = {};
  orderBookProcess?: IOrderBookProcess = {};
}

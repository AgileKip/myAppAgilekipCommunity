import axios from 'axios';
import { TaskAddBookContext } from './task-add-book.model';

const baseApiUrl = 'api/order-book-process/task-add-book';

export default class TaskAddBookService {
  public loadContext(taskId: number): Promise<TaskAddBookContext> {
    return new Promise<TaskAddBookContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<TaskAddBookContext> {
    return new Promise<TaskAddBookContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(taskAddBookContext: TaskAddBookContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskAddBookContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}

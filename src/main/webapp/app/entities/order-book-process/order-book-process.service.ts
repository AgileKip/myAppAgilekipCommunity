import axios from 'axios';

import { IOrderBookProcess } from '@/shared/model/order-book-process.model';

const baseApiUrl = 'api/order-book-processes';

export default class OrderBookProcessService {
  public find(id: number): Promise<IOrderBookProcess> {
    return new Promise<IOrderBookProcess>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IOrderBookProcess): Promise<IOrderBookProcess> {
    return new Promise<IOrderBookProcess>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}

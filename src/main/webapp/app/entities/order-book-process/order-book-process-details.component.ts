import { Component, Vue, Inject } from 'vue-property-decorator';

import { IOrderBookProcess } from '@/shared/model/order-book-process.model';
import OrderBookProcessService from './order-book-process.service';

@Component
export default class OrderBookProcessDetailsComponent extends Vue {
  @Inject('orderBookProcessService') private orderBookProcessService: () => OrderBookProcessService;
  public orderBookProcess: IOrderBookProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveOrderBookProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveOrderBookProcess(orderBookProcessId) {
    this.isFetching = true;
    this.orderBookProcessService()
      .find(orderBookProcessId)
      .then(
        res => {
          this.orderBookProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}

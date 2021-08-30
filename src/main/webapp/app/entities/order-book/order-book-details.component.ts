import { Component, Vue, Inject } from 'vue-property-decorator';

import { IOrderBook } from '@/shared/model/order-book.model';
import OrderBookService from './order-book.service';

@Component
export default class OrderBookDetails extends Vue {
  @Inject('orderBookService') private orderBookService: () => OrderBookService;
  public orderBook: IOrderBook = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.orderBookId) {
        vm.retrieveOrderBook(to.params.orderBookId);
      }
    });
  }

  public retrieveOrderBook(orderBookId) {
    this.orderBookService()
      .find(orderBookId)
      .then(res => {
        this.orderBook = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

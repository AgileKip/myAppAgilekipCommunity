import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IOrderBook } from '@/shared/model/order-book.model';

import OrderBookService from './order-book.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class OrderBook extends Vue {
  @Inject('orderBookService') private orderBookService: () => OrderBookService;
  private removeId: number = null;

  public orderBooks: IOrderBook[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllOrderBooks();
  }

  public clear(): void {
    this.retrieveAllOrderBooks();
  }

  public retrieveAllOrderBooks(): void {
    this.isFetching = true;

    this.orderBookService()
      .retrieve()
      .then(
        res => {
          this.orderBooks = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IOrderBook): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeOrderBook(): void {
    this.orderBookService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('myAppAgilekipCommunityApp.orderBook.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllOrderBooks();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}

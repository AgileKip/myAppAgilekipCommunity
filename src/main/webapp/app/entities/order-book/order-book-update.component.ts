import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required } from 'vuelidate/lib/validators';

import OrderService from '@/entities/order/order.service';
import { IOrder } from '@/shared/model/order.model';

import BookService from '@/entities/book/book.service';
import { IBook } from '@/shared/model/book.model';

import { IOrderBook, OrderBook } from '@/shared/model/order-book.model';
import OrderBookService from './order-book.service';

const validations: any = {
  orderBook: {
    quantity: {
      required,
      numeric,
    },
  },
};

@Component({
  validations,
})
export default class OrderBookUpdate extends Vue {
  @Inject('orderBookService') private orderBookService: () => OrderBookService;
  public orderBook: IOrderBook = new OrderBook();

  @Inject('orderService') private orderService: () => OrderService;

  public orders: IOrder[] = [];

  @Inject('bookService') private bookService: () => BookService;

  public books: IBook[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.orderBookId) {
        vm.retrieveOrderBook(to.params.orderBookId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.orderBook.id) {
      this.orderBookService()
        .update(this.orderBook)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('myAppAgilekipCommunityApp.orderBook.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.orderBookService()
        .create(this.orderBook)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('myAppAgilekipCommunityApp.orderBook.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveOrderBook(orderBookId): void {
    this.orderBookService()
      .find(orderBookId)
      .then(res => {
        this.orderBook = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.orderService()
      .retrieve()
      .then(res => {
        this.orders = res.data;
      });
    this.bookService()
      .retrieve()
      .then(res => {
        this.books = res.data;
      });
  }
}

import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import UserService from '@/admin/user-management/user-management.service';

import StoreService from '@/entities/store/store.service';
import { IStore } from '@/shared/model/store.model';

import OrderBookService from '@/entities/order-book/order-book.service';
import { IOrderBook } from '@/shared/model/order-book.model';

import { IOrder, Order } from '@/shared/model/order.model';
import OrderService from './order.service';

const validations: any = {
  order: {
    number: {
      required,
    },
    streetAddress: {},
    postalCode: {},
    city: {},
    stateProvince: {},
    deliveryMethod: {},
    ccNumber: {},
    ccDate: {},
    ccVerifierDigit: {},
    total: {},
  },
};

@Component({
  validations,
})
export default class OrderUpdate extends Vue {
  @Inject('orderService') private orderService: () => OrderService;
  public order: IOrder = new Order();

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];

  @Inject('storeService') private storeService: () => StoreService;

  public stores: IStore[] = [];

  @Inject('orderBookService') private orderBookService: () => OrderBookService;

  public orderBooks: IOrderBook[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.orderId) {
        vm.retrieveOrder(to.params.orderId);
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
    if (this.order.id) {
      this.orderService()
        .update(this.order)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('myAppAgilekipCommunityApp.order.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.orderService()
        .create(this.order)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('myAppAgilekipCommunityApp.order.created', { param: param.id });
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

  public retrieveOrder(orderId): void {
    this.orderService()
      .find(orderId)
      .then(res => {
        this.order = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.userService()
      .retrieve()
      .then(res => {
        this.users = res.data;
      });
    this.storeService()
      .retrieve()
      .then(res => {
        this.stores = res.data;
      });
    this.orderBookService()
      .retrieve()
      .then(res => {
        this.orderBooks = res.data;
      });
  }
}

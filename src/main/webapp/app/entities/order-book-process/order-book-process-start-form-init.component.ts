import { Component, Vue, Inject } from 'vue-property-decorator';

import BookService from '@/entities/book/book.service';
import { IBook } from '@/shared/model/book.model';

import UserService from '@/admin/user-management/user-management.service';


import { IOrderBookProcess, OrderBookProcess } from '@/shared/model/order-book-process.model';
import { ProcessInstance } from '@/shared/model/process-instance.model';
import { Order } from '@/shared/model/order.model';

import OrderBookProcessService from './order-book-process.service';
import ProcessDefinitionService from '@/entities/process-definition/process-definition.service';

const validations: any = {
  orderBookProcess: {
    order: {
      number: {},
    },
  },
};

@Component({
  validations,
})
export default class OrderBookProcessStartFormInitComponent extends Vue {
  @Inject('orderBookProcessService') private orderBookProcessService: () => OrderBookProcessService;
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;
  @Inject('bookService') private bookService: () => BookService;
  @Inject('userService') private userService: () => UserService;

  public bpmnProcessDefinitionId: string = 'OrderBookProcess';
  public orderBookProcess: IOrderBookProcess = new OrderBookProcess();
  public isSaving = false;
  public currentLanguage = '';
  public books: IBook[] = [];
  public users: Array<any> = [];

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initOrderBookProcessStartForm();
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

    this.orderBookProcessService()
      .create(this.orderBookProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('myAppAgilekipCommunityApp.orderBookProcessStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initOrderBookProcessStartForm(): void {
    this.orderBookProcess.order = new Order();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService()
      .find(this.bpmnProcessDefinitionId)
      .then(res => {
        this.orderBookProcess.processInstance = new ProcessInstance();
        this.orderBookProcess.processInstance.processDefinition = res;
      });

    this.bookService()
        .retrieve()
        .then(res => {
          this.books = res.data;
        })

    this.userService()
        .retrieve()
        .then(res => {
          this.users = res.data;
        });
  }
}

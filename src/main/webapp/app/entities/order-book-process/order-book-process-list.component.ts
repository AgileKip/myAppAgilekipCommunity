import { Component, Vue, Inject } from 'vue-property-decorator';
import { IProcessDefinition } from '@/shared/model/process-definition.model';
import { IOrderBookProcess } from '@/shared/model/order-book-process.model';

import ProcessDefinitionService from '@/entities/process-definition/process-definition.service';
import OrderBookProcessService from './order-book-process.service';

@Component
export default class OrderBookProcessListComponent extends Vue {
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;
  @Inject('orderBookProcessService') private orderBookProcessService: () => OrderBookProcessService;

  public bpmnProcessDefinitionId: string = 'OrderBookProcess';
  public processDefinition: IProcessDefinition = {};
  public orderBookProcessList: IOrderBookProcess[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  public mounted(): void {
    this.init();
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService()
      .find(this.bpmnProcessDefinitionId)
      .then(
        res => {
          this.processDefinition = res;
          this.isFetchingProcessDefinition = false;
        },
        err => {
          this.isFetchingProcessDefinition = false;
        }
      );
  }

  public retrieveProcessInstances(): void {
    this.isFetchingProcessInstances = true;
    this.orderBookProcessService()
      .retrieve()
      .then(
        res => {
          this.orderBookProcessList = res.data;
          this.isFetchingProcessInstances = false;
        },
        err => {
          this.isFetchingProcessInstances = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessInstances;
  }

  public handleSyncList(): void {
    this.retrieveProcessInstances();
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}

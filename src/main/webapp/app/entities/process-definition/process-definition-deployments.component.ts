import { Component, Inject, Vue } from 'vue-property-decorator';

import { IProcessDefinition } from '@/shared/model/process-definition.model';
import { IProcessDeployment } from '@/shared/model/process-deployment.model';

import ProcessDefinitionService from '@/entities/process-definition/process-definition.service';
import { StatusProcessDeployment } from '@/shared/model/enumerations/status-process-deployment.model';

@Component
export default class ProcessDefinitionDeployments extends Vue {
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;

  public processDefinitionId: any = 0;
  public processDefinition: IProcessDefinition = {};
  public processDeployments: IProcessDeployment[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessDeployments = false;

  public collapseController: any = { showActiveDeployments: true, showInactiveDeployments: false };

  public collapse(collapseComponent: string): void {
    this.collapseController[collapseComponent] = !this.collapseController[collapseComponent];
  }

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processDefinitionId) {
        vm.processDefinitionId = to.params.processDefinitionId;
        vm.init();
      }
    });
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessDeployments();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService()
      .find(this.processDefinitionId)
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

  public retrieveProcessDeployments(): void {
    this.isFetchingProcessDeployments = true;
    this.processDefinitionService()
      .findProcessDeployments(this.processDefinitionId)
      .then(
        res => {
          this.processDeployments = res;
          this.isFetchingProcessDeployments = false;
        },
        err => {
          this.isFetchingProcessDeployments = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessDeployments;
  }

  public handleSyncList(): void {
    this.retrieveProcessDeployments();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  get activeProcessDeployments(): IProcessDeployment[] {
    return this.processDeployments.filter(processDeployment => processDeployment.status == StatusProcessDeployment.ACTIVE);
  }

  get inactiveProcessDeployments(): IProcessDeployment[] {
    return this.processDeployments.filter(processDeployment => processDeployment.status == StatusProcessDeployment.INACTIVE);
  }
}

import {Component, Inject, Vue} from 'vue-property-decorator';

import {IProcessDeployment} from "@/shared/model/process-deployment.model";
import ProcessDeploymentService from './process-deployment.service';
import {StatusProcessDeployment} from "@/shared/model/enumerations/status-process-deployment.model";

@Component
export default class ProcessDeploymentDetails extends Vue {
  @Inject('processDeploymentService') private processDeploymentService: () => ProcessDeploymentService;

  public processDeployment: IProcessDeployment = {};

  public isFetching: boolean = false;

  public collapseController: any = { showProcess: true, showInstances: true };

  public collapse(collapseComponent:string):void {
    this.collapseController[collapseComponent] = !this.collapseController[collapseComponent];
  }

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processDeploymentId) {
        vm.retrieveProcessDeployment(to.params.processDeploymentId);
      }
    });
  }

  public retrieveProcessDeployment(id) {
    this.isFetching = true;
    this.processDeploymentService()
      .find(id)
      .then(
        res => {
          this.processDeployment = res;
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

  get isActive() {
    if (!this.processDeployment.status) {
      return false;
    }
    return this.processDeployment.status == StatusProcessDeployment.ACTIVE;
  }
}

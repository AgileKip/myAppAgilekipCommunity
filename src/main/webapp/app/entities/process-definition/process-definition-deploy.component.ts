import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import NotifyMixin from '@/shared/notify/notify.mixin';
import ProcessDeploymentService from '@/entities/process-deployment/process-deployment.service';
import { IProcessDeployment, ProcessDeployment } from '@/shared/model/process-deployment.model';
import { required } from 'vuelidate/lib/validators';

const validations: any = {
  processDeployment: {
    specificationFile: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class ProcessDefinitionDeploy extends mixins(JhiDataUtils, NotifyMixin) {
  @Inject('processDeploymentService') private processDeploymentService: () => ProcessDeploymentService;

  public processDeployment: IProcessDeployment = new ProcessDeployment();
  public isDeploying = false;

  public deploy(): void {
    this.isDeploying = true;

    this.processDeploymentService()
      .deploy(this.processDeployment)
      .then(param => {
        this.isDeploying = false;
        this.notifySuccessFromResponse(param);
        this.$router.go(-1);
      })
      .catch(response => {
        this.isDeploying = false;
        this.notifyErrorFromResponse(response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}

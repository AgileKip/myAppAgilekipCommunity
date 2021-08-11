import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IProcessDefinition } from '@/shared/model/process-definition.model';

import JhiDataUtils from '@/shared/data/data-utils.service';

import ProcessDefinitionService from './process-definition.service';
import { ITaskInstance } from '@/shared/model/task-instance.model';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ProcessDefinition extends mixins(JhiDataUtils) {
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;

  private processDefinitionToRemove: IProcessDefinition = null;

  public processDefinitions: IProcessDefinition[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllProcessDefinitions();
  }

  public clear(): void {
    this.retrieveAllProcessDefinitions();
  }

  public retrieveAllProcessDefinitions(): void {
    this.isFetching = true;

    this.processDefinitionService()
      .retrieve()
      .then(
        res => {
          this.processDefinitions = res.data;
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

  public prepareRemove(processDefinition: IProcessDefinition): void {
    this.processDefinitionToRemove = processDefinition;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeProcessDefinition(): void {
    this.processDefinitionService()
      .delete(this.processDefinitionToRemove.id)
      .then(() => {
        const message = this.$t('myAppAgilekipCommunityApp.processDefinition.deleted', { param: this.processDefinitionToRemove.name });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.processDefinitionToRemove = null;
        this.retrieveAllProcessDefinitions();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}

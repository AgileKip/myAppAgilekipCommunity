<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="processDeployment">
        <h2 class="jh-entity-heading" data-cy="processDeploymentDetailsHeading">
          <span v-text="$t('myAppAgilekipCommunityApp.processDeployment.detail.title')">ProcessDeployment</span>
        </h2>
        <div class="card mb-3">
          <h5 class="card-header">
            <span class="title">
              <span v-text="$t('myAppAgilekipCommunityApp.processDeployment.detail.card-header')">Process Deployment</span>
              #{{ processDeployment.id }}
            </span>
            <akip-show-process-deployment-status :status="processDeployment.status"></akip-show-process-deployment-status>
          </h5>
          <div class="card-body">
            <div class="form-group row">
              <div class="col-sm">
                <span class="label" v-text="$t('myAppAgilekipCommunityApp.processInstance.processDefinition')">Process Definition </span>:
                <br />
                <span class="link" v-if="processDeployment.processDefinition">
                  <router-link class="link" :to="`/process-definition/${processDeployment.processDefinition.bpmnProcessDefinitionId}/view`">
                    {{ processDeployment.processDefinition.name }}
                  </router-link>
                </span>
              </div>

              <div class="col-sm">
                <span class="label" v-text="$t('myAppAgilekipCommunityApp.processDeployment.camundaProcessDefinitionId')"
                  >Process Definition </span
                >:
                <br />
                <strong>{{ processDeployment.camundaProcessDefinitionId }}</strong>
              </div>

              <div class="col-sm">
                <span class="label" v-text="$t('myAppAgilekipCommunityApp.processDeployment.camundaDeploymentId')">Process Definition </span
                >:
                <br />
                <strong>{{ processDeployment.camundaDeploymentId }}</strong>
              </div>

              <div class="col-sm">
                <span class="label" v-text="$t('myAppAgilekipCommunityApp.processDeployment.tenant')">Tenant </span>:
                <br />
                <strong v-if="processDeployment.tenant">{{ processDeployment.tenant.name }}</strong>
              </div>
            </div>
            <div class="form-group row">
              <div class="col-sm-3">
                <span class="label" v-text="$t('myAppAgilekipCommunityApp.processDeployment.deployDate')">Deploy Date</span>:
                <br />
                <strong>{{ processDeployment.deployDate ? $d(Date.parse(processDeployment.deployDate), 'short') : '' }}</strong>
              </div>

              <div class="col-sm-3" v-if="isActive">
                <span class="label" v-text="$t('myAppAgilekipCommunityApp.processDeployment.activationDate')">Activation Date</span>:
                <br />
                <strong>{{ processDeployment.activationDate ? $d(Date.parse(processDeployment.activationDate), 'short') : '' }}</strong>
              </div>
              <div class="col-sm-3" v-else>
                <span class="label" v-text="$t('myAppAgilekipCommunityApp.processDeployment.inactivationDate')">Inactivation Date</span>:
                <br />
                <strong>{{ processDeployment.inactivationDate ? $d(Date.parse(processDeployment.inactivationDate), 'short') : '' }}</strong>
              </div>
            </div>
            <div class="card">
              <h4 class="card-header collapse-link" v-on:click="collapse('showProcess')">
                Process
                <font-awesome-icon icon="compress-alt" v-if="collapseController.showProcess"></font-awesome-icon>
                <font-awesome-icon icon="expand-alt" v-else></font-awesome-icon>
              </h4>
              <b-collapse v-model="collapseController.showProcess" id="collapse-inactive-deployments">
                <akip-show-process-deployment-bpmn-model
                  :processDeploymentId="processDeployment.id"
                ></akip-show-process-deployment-bpmn-model>
              </b-collapse>
            </div>
          </div>
        </div>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./process-deployment-details.component.ts"></script>

<style scoped>
.title {
  float: left;
  padding-right: 0.55em;
}
</style>

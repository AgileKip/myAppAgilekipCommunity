<template>
  <div class="card" v-if="processInstance">
    <h5 class="card-header">
      <span class="title">
        <slot name="title">
          <span v-if="processInstance.processDefinition">
            {{ processInstance.processDefinition.name }}
          </span>
          #{{ processInstance.id }}
        </slot>
      </span>
      <akip-show-process-instance-status :status="processInstance.status"></akip-show-process-instance-status>
    </h5>
    <div class="card-body">
      <div>
        <div class="row">
          <div class="col-sm">
            <slot name="details">
              <div class="form-group">
                <label
                  class="form-control-label"
                  v-text="$t('myAppAgilekipCommunityApp.processInstance.businessKey')"
                  for="process-instance-business-key"
                  >Business Key</label
                >
                <input
                  readonly
                  type="text"
                  class="form-control"
                  name="name"
                  id="process-instance-business-key"
                  data-cy="name"
                  v-model="processInstance.businessKey"
                />
              </div>
            </slot>
            <slot name="tasks">
              <hr />
              <div class="card">
                <h4 class="card-header collapse-link" v-on:click="collapse('showTasks')">
                  Tasks
                  <font-awesome-icon icon="compress-alt" v-if="collapseController.showTasks"></font-awesome-icon>
                  <font-awesome-icon icon="expand-alt" v-else></font-awesome-icon>
                </h4>
                <b-collapse v-model="collapseController.showTasks" id="collapse-tasks">
                  <div class="card-body p-0">
                    <akip-table-task-instances
                      :processInstance="processInstance"
                      :columns="['name', 'status', 'createDate', 'endTime']"
                    ></akip-table-task-instances>
                  </div>
                </b-collapse>
              </div>
            </slot>

            <slot name="process">
              <hr />
              <div class="card">
                <h4 class="card-header collapse-link" v-on:click="collapse('showProcess')">
                  Process
                  <font-awesome-icon icon="compress-alt" v-if="collapseController.showProcess"></font-awesome-icon>
                  <font-awesome-icon icon="expand-alt" v-else></font-awesome-icon>
                </h4>
                <b-collapse v-model="collapseController.showProcess" id="collapse-process" class="p-3">
                  <div class="row summary" v-if="processInstance.processDefinition">
                    <div class="col-sm">
                      <span class="label" v-text="$t('myAppAgilekipCommunityApp.processInstance.processDefinition')">
                        Process Definition </span
                      >:
                      <span class="link">
                        <router-link
                          class="link"
                          :to="`/process-definition/${processInstance.processDefinition.bpmnProcessDefinitionId}/view`"
                        >
                          {{ processInstance.processDefinition.name }}
                        </router-link>
                      </span>
                    </div>
                    <div class="col-sm">
                      <span class="label" v-text="$t('myAppAgilekipCommunityApp.processInstance.camundaProcessInstanceId')">
                        Camunda Process Instance Id </span
                      >:
                      {{ processInstance.camundaProcessInstanceId }}
                    </div>
                    <div class="col-sm">
                      <span class="label" v-text="$t('myAppAgilekipCommunityApp.processInstance.camundaProcessDefinitionId')">
                        Camunda Process Definition Id </span
                      >:
                      {{ processInstance.camundaProcessDefinitionId }}
                    </div>
                    <div class="col-sm">
                      <span class="label" v-text="$t('myAppAgilekipCommunityApp.processDefinition.bpmnProcessDefinitionId')">
                        BPMN Process Definition Id </span
                      >:
                      <span class="link" v-if="processInstance.processDefinition.bpmnProcessDefinitionId">
                        {{ processInstance.processDefinition.bpmnProcessDefinitionId }}
                      </span>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-12">
                      <akip-show-process-instance-bpmn-model :processInstance="processInstance"></akip-show-process-instance-bpmn-model>
                    </div>
                  </div>
                </b-collapse>
              </div>
            </slot>
          </div>
        </div>
        <hr />
        <div class="row summary-footer">
          <div class="col-12">
            <span class="footer-entry" v-if="processInstance.startDate">
              <font-awesome-icon icon="clock"></font-awesome-icon>
              Created at: {{ $d(Date.parse(processInstance.startDate), 'long') }}
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./akip-show-process-instance.component.ts"></script>

<style scoped>
.title {
  float: left;
  padding-right: 0.55em;
}

.summary-footer {
  font-size: 85%;
  color: gray;
}

.footer-entry {
  padding-right: 0.95em;
}

.summary {
  color: #586069;
  font-size: 80%;
}

.summary hr {
  margin-top: 0.5em;
  margin-bottom: 0.5em;
}

.label {
  font-weight: bold;
  margin-bottom: 0.25em;
}

.link a {
  font-weight: normal;
  color: #3e8acc;
}

.collapse-link {
  cursor: pointer;
}

.collapse-link > svg {
  color: #737373;
  margin-left: 0.25em;
}
</style>

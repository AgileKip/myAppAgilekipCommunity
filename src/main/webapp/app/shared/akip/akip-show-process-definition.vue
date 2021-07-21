<template>
  <div class="card" v-if="processDefinition">
    <h5 class="card-header">
      <span class="title">
        #{{ processDefinition.id }} -
        {{ processDefinition.name }}
      </span>
    </h5>
    <div class="card-body">
      <div class="xcontainer">
        <div class="row px-3">
          <div class="col-sm description">
            <div v-if="processDefinition.description">
              <vue-markdown>{{ processDefinition.description }}</vue-markdown>
            </div>
            <slot name="body"></slot>
          </div>
        </div>
        <div class="row summary mt-3">
          <div class="col-sm">
            <span class="label" v-text="$t('myAppAgilekipCommunityApp.processDefinition.bpmnProcessDefinitionId')">
              BPMN Process Definition Id </span
            >:
            {{ processDefinition.bpmnProcessDefinitionId }}
          </div>
          <div class="col-sm">
            <span class="label" v-text="$t('myAppAgilekipCommunityApp.processDefinition.camundaProcessDefinitionId')">
              Camunda Process Definition Id </span
            >:
            {{ processDefinition.camundaProcessDefinitionId }}
          </div>
          <div class="col-sm">
            <span class="label" v-text="$t('myAppAgilekipCommunityApp.processDefinition.camundaDeploymentId')"> Camunda Deployment Id </span
            >:
            {{ processDefinition.camundaDeploymentId }}
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <akip-show-process-definition-bpmn-model :processDefinition="processDefinition"></akip-show-process-definition-bpmn-model>
          </div>
        </div>
        <div class="row summary-footer">
          <span class="footer-entry" v-if="processDefinition.createTime">
            <font-awesome-icon icon="clock"></font-awesome-icon>
            Created at: {{ $d(Date.parse(processDefinition.createTime), 'long') }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./akip-show-process-definition.component.ts"></script>

<style scoped>
.title {
  float: left;
  padding-right: 0.55em;
}

.description {
  border: 1px solid lightgray !important;
  border-radius: 6px;
  padding-top: 10px;
}

.summary-footer {
  padding-top: 0.45em;
  font-size: 95%;
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
</style>

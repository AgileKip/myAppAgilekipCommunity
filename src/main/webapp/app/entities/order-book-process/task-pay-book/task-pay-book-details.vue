<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('myAppAgilekipCommunityApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.taskPayBook.number')">number</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="number"
                id="order-number"
                data-cy="number"
                v-model="taskContext.orderBookProcess.order.number"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.taskPayBook.total')">total</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="total"
                id="order-total"
                data-cy="total"
                v-model="taskContext.orderBookProcess.order.total"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.taskPayBook.ccNumber')">ccNumber</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="ccNumber"
                id="order-ccNumber"
                data-cy="ccNumber"
                v-model="taskContext.orderBookProcess.order.ccNumber"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.taskPayBook.ccDate')">ccDate</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="ccDate"
                id="order-ccDate"
                data-cy="ccDate"
                v-model="taskContext.orderBookProcess.order.ccDate"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.taskPayBook.ccVerifierDigit')">ccVerifierDigit</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="ccVerifierDigit"
                id="order-ccVerifierDigit"
                data-cy="ccVerifierDigit"
                v-model="taskContext.orderBookProcess.order.ccVerifierDigit"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/OrderBookProcess/task/TaskPayBook/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-pay-book-details.component.ts"></script>

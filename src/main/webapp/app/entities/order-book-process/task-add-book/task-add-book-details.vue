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
              <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.taskAddBook.number')">number</label>
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
              <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.taskAddBook.book')" for="task-add-book-book"
                >Book</label
              >
              <input
                v-if="taskContext.orderBookProcess.order.book"
                readonly
                type="text"
                class="form-control"
                name="book"
                id="order-book"
                data-cy="book"
                :value="taskContext.orderBookProcess.order.book.title"
              />
              <input v-else readonly type="text" class="form-control" name="book" id="order-book" data-cy="book" value="" />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/OrderBookProcess/task/TaskAddBook/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./task-add-book-details.component.ts"></script>

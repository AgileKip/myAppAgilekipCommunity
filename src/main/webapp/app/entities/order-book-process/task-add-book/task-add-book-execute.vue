<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('myAppAgilekipCommunityApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.taskAddBook.number')" for="task-add-book-number"
                >Number</label
              >
              <input
                type="text"
                class="form-control"
                name="number"
                id="task-add-book-number"
                data-cy="number"
                :class="{
                  valid: !$v.taskContext.orderBookProcess.order.number.$invalid,
                  invalid: $v.taskContext.orderBookProcess.order.number.$invalid,
                }"
                v-model="$v.taskContext.orderBookProcess.order.number.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.taskAddBook.book')" for="task-add-book-book"
                >Book</label
              >
              <select
                class="form-control"
                id="task-add-book-book"
                data-cy="book"
                name="book"
                v-model="taskContext.orderBookProcess.order.book"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.orderBookProcess.order.book && bookOption.id === taskContext.orderBookProcess.order.book.id
                      ? taskContext.orderBookProcess.order.book
                      : bookOption
                  "
                  v-for="bookOption in books"
                  :key="bookOption.id"
                >
                  {{ bookOption.title }}
                </option>
              </select>
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-add-book-execute.component.ts"></script>

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
              <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.taskPayBook.number')" for="task-pay-book-number"
                >Number</label
              >
              <input
                  readonly
                type="text"
                class="form-control"
                name="number"
                id="task-pay-book-number"
                data-cy="number"
                :class="{
                  valid: !$v.taskContext.orderBookProcess.order.number.$invalid,
                  invalid: $v.taskContext.orderBookProcess.order.number.$invalid,
                }"
                v-model="$v.taskContext.orderBookProcess.order.number.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.taskPayBook.total')" for="task-pay-book-total"
                >Total</label
              >
              <input
                  readonly
                type="text"
                class="form-control"
                name="total"
                id="task-pay-book-total"
                data-cy="total"
                :class="{
                  valid: !$v.taskContext.orderBookProcess.order.total.$invalid,
                  invalid: $v.taskContext.orderBookProcess.order.total.$invalid,
                }"
                v-model="$v.taskContext.orderBookProcess.order.total.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.taskPayBook.ccNumber')" for="task-pay-book-ccNumber"
                >Cc Number</label
              >
              <input
                type="text"
                class="form-control"
                name="ccNumber"
                id="task-pay-book-ccNumber"
                data-cy="ccNumber"
                :class="{
                  valid: !$v.taskContext.orderBookProcess.order.ccNumber.$invalid,
                  invalid: $v.taskContext.orderBookProcess.order.ccNumber.$invalid,
                }"
                v-model="$v.taskContext.orderBookProcess.order.ccNumber.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.taskPayBook.ccDate')" for="task-pay-book-ccDate"
                >Cc Date</label
              >
              <input
                type="text"
                class="form-control"
                name="ccDate"
                id="task-pay-book-ccDate"
                data-cy="ccDate"
                :class="{
                  valid: !$v.taskContext.orderBookProcess.order.ccDate.$invalid,
                  invalid: $v.taskContext.orderBookProcess.order.ccDate.$invalid,
                }"
                v-model="$v.taskContext.orderBookProcess.order.ccDate.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('myAppAgilekipCommunityApp.taskPayBook.ccVerifierDigit')"
                for="task-pay-book-ccVerifierDigit"
                >Cc Verifier Digit</label
              >
              <input
                type="text"
                class="form-control"
                name="ccVerifierDigit"
                id="task-pay-book-ccVerifierDigit"
                data-cy="ccVerifierDigit"
                :class="{
                  valid: !$v.taskContext.orderBookProcess.order.ccVerifierDigit.$invalid,
                  invalid: $v.taskContext.orderBookProcess.order.ccVerifierDigit.$invalid,
                }"
                v-model="$v.taskContext.orderBookProcess.order.ccVerifierDigit.$model"
              />
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

<script lang="ts" src="./task-pay-book-execute.component.ts"></script>

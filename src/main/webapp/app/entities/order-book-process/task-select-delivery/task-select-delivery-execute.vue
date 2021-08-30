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
              <label
                class="form-control-label"
                v-text="$t('myAppAgilekipCommunityApp.taskSelectDelivery.number')"
                for="task-select-delivery-number"
                >Number</label
              >
              <input
                type="text"
                class="form-control"
                name="number"
                id="task-select-delivery-number"
                data-cy="number"
                :class="{
                  valid: !$v.taskContext.orderBookProcess.order.number.$invalid,
                  invalid: $v.taskContext.orderBookProcess.order.number.$invalid,
                }"
                v-model="$v.taskContext.orderBookProcess.order.number.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('myAppAgilekipCommunityApp.taskSelectDelivery.deliveryMethod')"
                for="task-select-delivery-deliveryMethod"
                >Delivery Method</label
              >
              <select
                class="form-control"
                name="deliveryMethod"
                :class="{
                  valid: !$v.taskContext.orderBookProcess.order.deliveryMethod.$invalid,
                  invalid: $v.taskContext.orderBookProcess.order.deliveryMethod.$invalid,
                }"
                v-model="$v.taskContext.orderBookProcess.order.deliveryMethod.$model"
                id="task-select-delivery-deliveryMethod"
                data-cy="deliveryMethod"
              >
                <option value="PICKUP" v-bind:label="$t('myAppAgilekipCommunityApp.DeliveryMethod.PICKUP')">PICKUP</option>
                <option value="DELIVERY" v-bind:label="$t('myAppAgilekipCommunityApp.DeliveryMethod.DELIVERY')">DELIVERY</option>
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

<script lang="ts" src="./task-select-delivery-execute.component.ts"></script>

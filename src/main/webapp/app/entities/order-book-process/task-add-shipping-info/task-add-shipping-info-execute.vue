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
                v-text="$t('myAppAgilekipCommunityApp.taskAddShippingInfo.number')"
                for="task-add-shipping-info-number"
                >Number</label
              >
              <input
                  readonly
                type="text"
                class="form-control"
                name="number"
                id="task-add-shipping-info-number"
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
                v-text="$t('myAppAgilekipCommunityApp.taskAddShippingInfo.streetAddress')"
                for="task-add-shipping-info-streetAddress"
                >Street Address</label
              >
              <input
                type="text"
                class="form-control"
                name="streetAddress"
                id="task-add-shipping-info-streetAddress"
                data-cy="streetAddress"
                :class="{
                  valid: !$v.taskContext.orderBookProcess.order.streetAddress.$invalid,
                  invalid: $v.taskContext.orderBookProcess.order.streetAddress.$invalid,
                }"
                v-model="$v.taskContext.orderBookProcess.order.streetAddress.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('myAppAgilekipCommunityApp.taskAddShippingInfo.postalCode')"
                for="task-add-shipping-info-postalCode"
                >Postal Code</label
              >
              <input
                type="text"
                class="form-control"
                name="postalCode"
                id="task-add-shipping-info-postalCode"
                data-cy="postalCode"
                :class="{
                  valid: !$v.taskContext.orderBookProcess.order.postalCode.$invalid,
                  invalid: $v.taskContext.orderBookProcess.order.postalCode.$invalid,
                }"
                v-model="$v.taskContext.orderBookProcess.order.postalCode.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('myAppAgilekipCommunityApp.taskAddShippingInfo.city')"
                for="task-add-shipping-info-city"
                >City</label
              >
              <input
                type="text"
                class="form-control"
                name="city"
                id="task-add-shipping-info-city"
                data-cy="city"
                :class="{
                  valid: !$v.taskContext.orderBookProcess.order.city.$invalid,
                  invalid: $v.taskContext.orderBookProcess.order.city.$invalid,
                }"
                v-model="$v.taskContext.orderBookProcess.order.city.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('myAppAgilekipCommunityApp.taskAddShippingInfo.stateProvince')"
                for="task-add-shipping-info-stateProvince"
                >State Province</label
              >
              <input
                type="text"
                class="form-control"
                name="stateProvince"
                id="task-add-shipping-info-stateProvince"
                data-cy="stateProvince"
                :class="{
                  valid: !$v.taskContext.orderBookProcess.order.stateProvince.$invalid,
                  invalid: $v.taskContext.orderBookProcess.order.stateProvince.$invalid,
                }"
                v-model="$v.taskContext.orderBookProcess.order.stateProvince.$model"
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

<script lang="ts" src="./task-add-shipping-info-execute.component.ts"></script>

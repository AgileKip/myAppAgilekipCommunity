<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="myAppAgilekipCommunityApp.orderBookProcessStartForm.home.createOrEditLabel"
          data-cy="OrderBookProcessStartFormCreateUpdateHeading"
          v-text="$t('myAppAgilekipCommunityApp.orderBookProcessStartForm.home.createOrEditLabel')"
        >
          Create or edit a OrderBookProcessStartForm
        </h2>
        <div v-if="orderBookProcess.processInstance">
          <akip-show-process-definition :processDefinition="orderBookProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div class="col-sm" v-if="orderBookProcess.order">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('myAppAgilekipCommunityApp.orderBookProcessStartForm.number')"
                    for="order-book-process-start-form-number"
                    >Number</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="number"
                    id="order-book-process-start-form-number"
                    data-cy="number"
                    :class="{ valid: !$v.orderBookProcess.order.number.$invalid, invalid: $v.orderBookProcess.order.number.$invalid }"
                    v-model="$v.orderBookProcess.order.number.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('myAppAgilekipCommunityApp.orderBookProcessStartForm.user')"
                    for="order-book-process-start-form-user"
                    >User</label
                  >
                  <select
                    class="form-control"
                    id="order-book-process-start-form-user"
                    data-cy="user"
                    name="user"
                    v-model="orderBookProcess.order.user"
                  >
                    <option v-bind:value="null"></option>
                    <option
                      v-bind:value="
                        orderBookProcess.order.user && userOption.id === orderBookProcess.order.user.id
                          ? orderBookProcess.order.user
                          : userOption
                      "
                      v-for="userOption in users"
                      :key="userOption.id"
                    >
                      {{ userOption.login }}
                    </option>
                  </select>
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.orderBookProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./order-book-process-start-form-init.component.ts"></script>

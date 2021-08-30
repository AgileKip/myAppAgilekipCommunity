<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="orderBookProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="orderBookProcessDetailsHeading">
          <span v-text="$t('myAppAgilekipCommunityApp.orderBookProcess.detail.title')">OrderBookProcess</span> {{ orderBookProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="orderBookProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('myAppAgilekipCommunityApp.orderBookProcess.detail.title')">OrderBookProcess</span> #{{ orderBookProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="orderBookProcess.order">
              <h5 class="card-header">
                <span class="title"> Order #{{ orderBookProcess.order.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.orderBookProcess.number')">number</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="number"
                    id="order-number"
                    data-cy="number"
                    v-model="orderBookProcess.order.number"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label
                      class="form-control-label"
                      v-text="$t('myAppAgilekipCommunityApp.orderBookProcess.user')"
                      for="order-book-process-user"
                  >User</label
                  >
                  <input
                      v-if="orderBookProcess.order.user"
                      readonly
                      type="text"
                      class="form-control"
                      name="user"
                      id="order-user"
                      data-cy="user"
                      :value="orderBookProcess.order.user.login"
                  />
                  <input v-else readonly type="text" class="form-control" name="user" id="order-user" data-cy="user" value="" />
                </div>
              </div>

              <div class="card-body py-0">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('myAppAgilekipCommunityApp.orderBookProcess.book')"
                    for="order-book-process-book"
                    >Book</label
                  >
                  <input
                    v-if="orderBookProcess.order.book"
                    readonly
                    type="text"
                    class="form-control"
                    name="book"
                    id="order-book"
                    data-cy="book"
                    :value="orderBookProcess.order.book.title"
                  />
                  <input v-else readonly type="text" class="form-control" name="book" id="order-book" data-cy="book" value="" />
                </div>
              </div>

              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.orderBookProcess.deliveryMethod')"
                  >deliveryMethod</label
                  >
                  <input
                      readonly
                      type="text"
                      class="form-control"
                      name="deliveryMethod"
                      id="order-deliveryMethod"
                      data-cy="deliveryMethod"
                      v-model="orderBookProcess.order.deliveryMethod"
                  />
                </div>
              </div>

            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./order-book-process-details.component.ts"></script>

<template>
  <div>
    <h2 class="jh-entity-heading" data-cy="orderBookProcessDetailsHeading">
      <span v-text="$t('myAppAgilekipCommunityApp.orderBookProcess.home.title')">OrderBookProcess</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('myAppAgilekipCommunityApp.orderBookProcess.home.refreshListLabel')">Refresh List</span>
        </button>

        <router-link :to="`/process-definition/OrderBookProcess/init`" tag="button" class="btn btn-primary mr-2">
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span v-text="$t('myAppAgilekipCommunityApp.orderBookProcess.home.createLabel')">
            Create a new Travel Plan Process Instance
          </span>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && orderBookProcessList && orderBookProcessList.length === 0">
      <span v-text="$t('myAppAgilekipCommunityApp.orderBookProcess.home.notFound')">No orderBookProcess found</span>
    </div>
    <div class="table-responsive" v-if="orderBookProcessList && orderBookProcessList.length > 0">
      <table class="table table-striped" aria-describedby="orderBookProcessList">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.orderBookProcess.processInstance')">Process Instance</span></th>
            <th scope="row">Order</th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.processInstance.status')">Status</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.processInstance.startDate')">Start Date</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.processInstance.endDate')">End Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="orderBookProcess in orderBookProcessList" :key="orderBookProcess.id" data-cy="entityTable">
            <td>{{ orderBookProcess.id }}</td>
            <td>
              <div v-if="orderBookProcess.processInstance">
                <router-link :to="`/process-definition/OrderBookProcess/instance/${orderBookProcess.processInstance.id}/view`">
                  {{ orderBookProcess.processInstance.businessKey }}
                </router-link>
              </div>
            </td>
            <td>
              <div v-if="orderBookProcess.order">
                <router-link :to="{ name: 'OrderView', params: { orderId: orderBookProcess.order.id } }">{{
                  orderBookProcess.order.id
                }}</router-link>
              </div>
            </td>
            <td>
              <akip-show-process-instance-status :status="orderBookProcess.processInstance.status"></akip-show-process-instance-status>
            </td>
            <td>
              {{ orderBookProcess.processInstance.startDate ? $d(Date.parse(orderBookProcess.processInstance.startDate), 'short') : '' }}
            </td>
            <td>{{ orderBookProcess.processInstance.endDate ? $d(Date.parse(orderBookProcess.processInstance.endDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="`/process-definition/OrderBookProcess/instance/${orderBookProcess.processInstance.id}/view`"
                  tag="button"
                  class="btn btn-info btn-sm details"
                  data-cy="entityDetailsButton"
                >
                  <font-awesome-icon icon="eye"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
      <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
    </button>
  </div>
</template>

<script lang="ts" src="./order-book-process-list.component.ts"></script>

<template>
  <div>
    <h2 id="page-heading" data-cy="OrderHeading">
      <span v-text="$t('myAppAgilekipCommunityApp.order.home.title')" id="order-heading">Orders</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('myAppAgilekipCommunityApp.order.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'OrderCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-order"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('myAppAgilekipCommunityApp.order.home.createLabel')"> Create a new Order </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && orders && orders.length === 0">
      <span v-text="$t('myAppAgilekipCommunityApp.order.home.notFound')">No orders found</span>
    </div>
    <div class="table-responsive" v-if="orders && orders.length > 0">
      <table class="table table-striped" aria-describedby="orders">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.order.number')">Number</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.order.streetAddress')">Street Address</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.order.postalCode')">Postal Code</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.order.city')">City</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.order.stateProvince')">State Province</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.order.deliveryMethod')">Delivery Method</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.order.ccNumber')">Cc Number</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.order.ccDate')">Cc Date</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.order.ccVerifierDigit')">Cc Verifier Digit</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.order.total')">Total</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.order.user')">User</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.order.store')">Store</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="order in orders" :key="order.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'OrderView', params: { orderId: order.id } }">{{ order.id }}</router-link>
            </td>
            <td>{{ order.number }}</td>
            <td>{{ order.streetAddress }}</td>
            <td>{{ order.postalCode }}</td>
            <td>{{ order.city }}</td>
            <td>{{ order.stateProvince }}</td>
            <td v-text="$t('myAppAgilekipCommunityApp.DeliveryMethod.' + order.deliveryMethod)">{{ order.deliveryMethod }}</td>
            <td>{{ order.ccNumber }}</td>
            <td>{{ order.ccDate }}</td>
            <td>{{ order.ccVerifierDigit }}</td>
            <td>{{ order.total }}</td>
            <td>
              {{ order.user ? order.user.login : '' }}
            </td>
            <td>
              <div v-if="order.store">
                <router-link :to="{ name: 'StoreView', params: { storeId: order.store.id } }">{{ order.store.name }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'OrderView', params: { orderId: order.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'OrderEdit', params: { orderId: order.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(order)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="myAppAgilekipCommunityApp.order.delete.question" data-cy="orderDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-order-heading" v-text="$t('myAppAgilekipCommunityApp.order.delete.question', { id: removeId })">
          Are you sure you want to delete this Order?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-order"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeOrder()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./order.component.ts"></script>

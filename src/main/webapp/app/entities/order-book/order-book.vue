<template>
  <div>
    <h2 id="page-heading" data-cy="OrderBookHeading">
      <span v-text="$t('myAppAgilekipCommunityApp.orderBook.home.title')" id="order-book-heading">Order Books</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('myAppAgilekipCommunityApp.orderBook.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'OrderBookCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-order-book"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('myAppAgilekipCommunityApp.orderBook.home.createLabel')"> Create a new Order Book </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && orderBooks && orderBooks.length === 0">
      <span v-text="$t('myAppAgilekipCommunityApp.orderBook.home.notFound')">No orderBooks found</span>
    </div>
    <div class="table-responsive" v-if="orderBooks && orderBooks.length > 0">
      <table class="table table-striped" aria-describedby="orderBooks">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.orderBook.quantity')">Quantity</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.orderBook.order')">Order</span></th>
            <th scope="row"><span v-text="$t('myAppAgilekipCommunityApp.orderBook.book')">Book</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="orderBook in orderBooks" :key="orderBook.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'OrderBookView', params: { orderBookId: orderBook.id } }">{{ orderBook.id }}</router-link>
            </td>
            <td>{{ orderBook.quantity }}</td>
            <td>
              <div v-if="orderBook.order">
                <router-link :to="{ name: 'OrderView', params: { orderId: orderBook.order.id } }">{{ orderBook.order.id }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="orderBook.book">
                <router-link :to="{ name: 'BookView', params: { bookId: orderBook.book.id } }">{{ orderBook.book.id }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'OrderBookView', params: { orderBookId: orderBook.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'OrderBookEdit', params: { orderBookId: orderBook.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(orderBook)"
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
        ><span
          id="myAppAgilekipCommunityApp.orderBook.delete.question"
          data-cy="orderBookDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-orderBook-heading" v-text="$t('myAppAgilekipCommunityApp.orderBook.delete.question', { id: removeId })">
          Are you sure you want to delete this Order Book?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-orderBook"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeOrderBook()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./order-book.component.ts"></script>

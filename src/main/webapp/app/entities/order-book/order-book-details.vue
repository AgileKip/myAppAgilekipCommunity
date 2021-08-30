<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="orderBook">
        <h2 class="jh-entity-heading" data-cy="orderBookDetailsHeading">
          <span v-text="$t('myAppAgilekipCommunityApp.orderBook.detail.title')">OrderBook</span> {{ orderBook.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.orderBook.quantity')">Quantity</span>
          </dt>
          <dd>
            <span>{{ orderBook.quantity }}</span>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.orderBook.order')">Order</span>
          </dt>
          <dd>
            <div v-if="orderBook.order">
              <router-link :to="{ name: 'OrderView', params: { orderId: orderBook.order.id } }">{{ orderBook.order.id }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.orderBook.book')">Book</span>
          </dt>
          <dd>
            <div v-if="orderBook.book">
              <router-link :to="{ name: 'BookView', params: { bookId: orderBook.book.id } }">{{ orderBook.book.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="orderBook.id"
          :to="{ name: 'OrderBookEdit', params: { orderBookId: orderBook.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./order-book-details.component.ts"></script>

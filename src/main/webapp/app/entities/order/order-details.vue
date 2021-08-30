<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="order">
        <h2 class="jh-entity-heading" data-cy="orderDetailsHeading">
          <span v-text="$t('myAppAgilekipCommunityApp.order.detail.title')">Order</span> {{ order.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.order.number')">Number</span>
          </dt>
          <dd>
            <span>{{ order.number }}</span>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.order.streetAddress')">Street Address</span>
          </dt>
          <dd>
            <span>{{ order.streetAddress }}</span>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.order.postalCode')">Postal Code</span>
          </dt>
          <dd>
            <span>{{ order.postalCode }}</span>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.order.city')">City</span>
          </dt>
          <dd>
            <span>{{ order.city }}</span>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.order.stateProvince')">State Province</span>
          </dt>
          <dd>
            <span>{{ order.stateProvince }}</span>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.order.deliveryMethod')">Delivery Method</span>
          </dt>
          <dd>
            <span v-text="$t('myAppAgilekipCommunityApp.DeliveryMethod.' + order.deliveryMethod)">{{ order.deliveryMethod }}</span>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.order.ccNumber')">Cc Number</span>
          </dt>
          <dd>
            <span>{{ order.ccNumber }}</span>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.order.ccDate')">Cc Date</span>
          </dt>
          <dd>
            <span>{{ order.ccDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.order.ccVerifierDigit')">Cc Verifier Digit</span>
          </dt>
          <dd>
            <span>{{ order.ccVerifierDigit }}</span>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.order.total')">Total</span>
          </dt>
          <dd>
            <span>{{ order.total }}</span>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.order.book')">Book</span>
          </dt>
          <dd>
            <div v-if="order.book">
              <router-link :to="{ name: 'BookView', params: { bookId: order.book.id } }">{{ order.book.title }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.order.user')">User</span>
          </dt>
          <dd>
            {{ order.user ? order.user.login : '' }}
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.order.store')">Store</span>
          </dt>
          <dd>
            <div v-if="order.store">
              <router-link :to="{ name: 'StoreView', params: { storeId: order.store.id } }">{{ order.store.name }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="order.id" :to="{ name: 'OrderEdit', params: { orderId: order.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./order-details.component.ts"></script>

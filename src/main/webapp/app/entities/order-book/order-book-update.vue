<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="myAppAgilekipCommunityApp.orderBook.home.createOrEditLabel"
          data-cy="OrderBookCreateUpdateHeading"
          v-text="$t('myAppAgilekipCommunityApp.orderBook.home.createOrEditLabel')"
        >
          Create or edit a OrderBook
        </h2>
        <div>
          <div class="form-group" v-if="orderBook.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="orderBook.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.orderBook.quantity')" for="order-book-quantity"
              >Quantity</label
            >
            <input
              type="number"
              class="form-control"
              name="quantity"
              id="order-book-quantity"
              data-cy="quantity"
              :class="{ valid: !$v.orderBook.quantity.$invalid, invalid: $v.orderBook.quantity.$invalid }"
              v-model.number="$v.orderBook.quantity.$model"
              required
            />
            <div v-if="$v.orderBook.quantity.$anyDirty && $v.orderBook.quantity.$invalid">
              <small class="form-text text-danger" v-if="!$v.orderBook.quantity.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.orderBook.quantity.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.orderBook.order')" for="order-book-order">Order</label>
            <select class="form-control" id="order-book-order" data-cy="order" name="order" v-model="orderBook.order">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="orderBook.order && orderOption.id === orderBook.order.id ? orderBook.order : orderOption"
                v-for="orderOption in orders"
                :key="orderOption.id"
              >
                {{ orderOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.orderBook.book')" for="order-book-book">Book</label>
            <select class="form-control" id="order-book-book" data-cy="book" name="book" v-model="orderBook.book">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="orderBook.book && bookOption.id === orderBook.book.id ? orderBook.book : bookOption"
                v-for="bookOption in books"
                :key="bookOption.id"
              >
                {{ bookOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.orderBook.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./order-book-update.component.ts"></script>

<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="myAppAgilekipCommunityApp.order.home.createOrEditLabel"
          data-cy="OrderCreateUpdateHeading"
          v-text="$t('myAppAgilekipCommunityApp.order.home.createOrEditLabel')"
        >
          Create or edit a Order
        </h2>
        <div>
          <div class="form-group" v-if="order.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="order.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.order.number')" for="order-number">Number</label>
            <input
              type="text"
              class="form-control"
              name="number"
              id="order-number"
              data-cy="number"
              :class="{ valid: !$v.order.number.$invalid, invalid: $v.order.number.$invalid }"
              v-model="$v.order.number.$model"
              required
            />
            <div v-if="$v.order.number.$anyDirty && $v.order.number.$invalid">
              <small class="form-text text-danger" v-if="!$v.order.number.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.order.streetAddress')" for="order-streetAddress"
              >Street Address</label
            >
            <input
              type="text"
              class="form-control"
              name="streetAddress"
              id="order-streetAddress"
              data-cy="streetAddress"
              :class="{ valid: !$v.order.streetAddress.$invalid, invalid: $v.order.streetAddress.$invalid }"
              v-model="$v.order.streetAddress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.order.postalCode')" for="order-postalCode"
              >Postal Code</label
            >
            <input
              type="text"
              class="form-control"
              name="postalCode"
              id="order-postalCode"
              data-cy="postalCode"
              :class="{ valid: !$v.order.postalCode.$invalid, invalid: $v.order.postalCode.$invalid }"
              v-model="$v.order.postalCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.order.city')" for="order-city">City</label>
            <input
              type="text"
              class="form-control"
              name="city"
              id="order-city"
              data-cy="city"
              :class="{ valid: !$v.order.city.$invalid, invalid: $v.order.city.$invalid }"
              v-model="$v.order.city.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.order.stateProvince')" for="order-stateProvince"
              >State Province</label
            >
            <input
              type="text"
              class="form-control"
              name="stateProvince"
              id="order-stateProvince"
              data-cy="stateProvince"
              :class="{ valid: !$v.order.stateProvince.$invalid, invalid: $v.order.stateProvince.$invalid }"
              v-model="$v.order.stateProvince.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.order.deliveryMethod')" for="order-deliveryMethod"
              >Delivery Method</label
            >
            <select
              class="form-control"
              name="deliveryMethod"
              :class="{ valid: !$v.order.deliveryMethod.$invalid, invalid: $v.order.deliveryMethod.$invalid }"
              v-model="$v.order.deliveryMethod.$model"
              id="order-deliveryMethod"
              data-cy="deliveryMethod"
            >
              <option value="PICKUP" v-bind:label="$t('myAppAgilekipCommunityApp.DeliveryMethod.PICKUP')">PICKUP</option>
              <option value="DELIVERY" v-bind:label="$t('myAppAgilekipCommunityApp.DeliveryMethod.DELIVERY')">DELIVERY</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.order.ccNumber')" for="order-ccNumber">Cc Number</label>
            <input
              type="text"
              class="form-control"
              name="ccNumber"
              id="order-ccNumber"
              data-cy="ccNumber"
              :class="{ valid: !$v.order.ccNumber.$invalid, invalid: $v.order.ccNumber.$invalid }"
              v-model="$v.order.ccNumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.order.ccDate')" for="order-ccDate">Cc Date</label>
            <input
              type="text"
              class="form-control"
              name="ccDate"
              id="order-ccDate"
              data-cy="ccDate"
              :class="{ valid: !$v.order.ccDate.$invalid, invalid: $v.order.ccDate.$invalid }"
              v-model="$v.order.ccDate.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.order.ccVerifierDigit')" for="order-ccVerifierDigit"
              >Cc Verifier Digit</label
            >
            <input
              type="text"
              class="form-control"
              name="ccVerifierDigit"
              id="order-ccVerifierDigit"
              data-cy="ccVerifierDigit"
              :class="{ valid: !$v.order.ccVerifierDigit.$invalid, invalid: $v.order.ccVerifierDigit.$invalid }"
              v-model="$v.order.ccVerifierDigit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.order.total')" for="order-total">Total</label>
            <input
              type="number"
              class="form-control"
              name="total"
              id="order-total"
              data-cy="total"
              :class="{ valid: !$v.order.total.$invalid, invalid: $v.order.total.$invalid }"
              v-model.number="$v.order.total.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.order.user')" for="order-user">User</label>
            <select class="form-control" id="order-user" data-cy="user" name="user" v-model="order.user">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="order.user && userOption.id === order.user.id ? order.user : userOption"
                v-for="userOption in users"
                :key="userOption.id"
              >
                {{ userOption.login }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.order.store')" for="order-store">Store</label>
            <select class="form-control" id="order-store" data-cy="store" name="store" v-model="order.store">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="order.store && storeOption.id === order.store.id ? order.store : storeOption"
                v-for="storeOption in stores"
                :key="storeOption.id"
              >
                {{ storeOption.name }}
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
            :disabled="$v.order.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./order-update.component.ts"></script>

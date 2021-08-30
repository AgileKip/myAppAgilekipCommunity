<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="myAppAgilekipCommunityApp.store.home.createOrEditLabel"
          data-cy="StoreCreateUpdateHeading"
          v-text="$t('myAppAgilekipCommunityApp.store.home.createOrEditLabel')"
        >
          Create or edit a Store
        </h2>
        <div>
          <div class="form-group" v-if="store.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="store.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.store.name')" for="store-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="store-name"
              data-cy="name"
              :class="{ valid: !$v.store.name.$invalid, invalid: $v.store.name.$invalid }"
              v-model="$v.store.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.store.city')" for="store-city">City</label>
            <input
              type="text"
              class="form-control"
              name="city"
              id="store-city"
              data-cy="city"
              :class="{ valid: !$v.store.city.$invalid, invalid: $v.store.city.$invalid }"
              v-model="$v.store.city.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.store.address')" for="store-address">Address</label>
            <input
              type="text"
              class="form-control"
              name="address"
              id="store-address"
              data-cy="address"
              :class="{ valid: !$v.store.address.$invalid, invalid: $v.store.address.$invalid }"
              v-model="$v.store.address.$model"
            />
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
            :disabled="$v.store.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./store-update.component.ts"></script>

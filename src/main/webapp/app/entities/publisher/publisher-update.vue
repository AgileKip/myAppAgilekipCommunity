<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="myAppAgilekipCommunityApp.publisher.home.createOrEditLabel"
          data-cy="PublisherCreateUpdateHeading"
          v-text="$t('myAppAgilekipCommunityApp.publisher.home.createOrEditLabel')"
        >
          Create or edit a Publisher
        </h2>
        <div>
          <div class="form-group" v-if="publisher.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="publisher.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.publisher.name')" for="publisher-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="publisher-name"
              data-cy="name"
              :class="{ valid: !$v.publisher.name.$invalid, invalid: $v.publisher.name.$invalid }"
              v-model="$v.publisher.name.$model"
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
            :disabled="$v.publisher.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./publisher-update.component.ts"></script>

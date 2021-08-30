<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="myAppAgilekipCommunityApp.book.home.createOrEditLabel"
          data-cy="BookCreateUpdateHeading"
          v-text="$t('myAppAgilekipCommunityApp.book.home.createOrEditLabel')"
        >
          Create or edit a Book
        </h2>
        <div>
          <div class="form-group" v-if="book.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="book.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.book.title')" for="book-title">Title</label>
            <input
              type="text"
              class="form-control"
              name="title"
              id="book-title"
              data-cy="title"
              :class="{ valid: !$v.book.title.$invalid, invalid: $v.book.title.$invalid }"
              v-model="$v.book.title.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.book.pubYear')" for="book-pubYear">Pub Year</label>
            <input
              type="text"
              class="form-control"
              name="pubYear"
              id="book-pubYear"
              data-cy="pubYear"
              :class="{ valid: !$v.book.pubYear.$invalid, invalid: $v.book.pubYear.$invalid }"
              v-model="$v.book.pubYear.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.book.price')" for="book-price">Price</label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="book-price"
              data-cy="price"
              :class="{ valid: !$v.book.price.$invalid, invalid: $v.book.price.$invalid }"
              v-model.number="$v.book.price.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('myAppAgilekipCommunityApp.book.publisher')" for="book-publisher">Publisher</label>
            <select class="form-control" id="book-publisher" data-cy="publisher" name="publisher" v-model="book.publisher">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="book.publisher && publisherOption.id === book.publisher.id ? book.publisher : publisherOption"
                v-for="publisherOption in publishers"
                :key="publisherOption.id"
              >
                {{ publisherOption.name }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="$t('myAppAgilekipCommunityApp.book.authors')" for="book-authors">Authors</label>
            <select
              class="form-control"
              id="book-authors"
              data-cy="authors"
              multiple
              name="authors"
              v-if="book.authors !== undefined"
              v-model="book.authors"
            >
              <option v-bind:value="getSelected(book.authors, authorOption)" v-for="authorOption in authors" :key="authorOption.id">
                {{ authorOption.name }}
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
            :disabled="$v.book.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./book-update.component.ts"></script>

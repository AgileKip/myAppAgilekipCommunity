<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="book">
        <h2 class="jh-entity-heading" data-cy="bookDetailsHeading">
          <span v-text="$t('myAppAgilekipCommunityApp.book.detail.title')">Book</span> {{ book.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.book.title')">Title</span>
          </dt>
          <dd>
            <span>{{ book.title }}</span>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.book.pubYear')">Pub Year</span>
          </dt>
          <dd>
            <span>{{ book.pubYear }}</span>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.book.price')">Price</span>
          </dt>
          <dd>
            <span>{{ book.price }}</span>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.book.publisher')">Publisher</span>
          </dt>
          <dd>
            <div v-if="book.publisher">
              <router-link :to="{ name: 'PublisherView', params: { publisherId: book.publisher.id } }">{{
                book.publisher.name
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('myAppAgilekipCommunityApp.book.authors')">Authors</span>
          </dt>
          <dd>
            <span v-for="(authors, i) in book.authors" :key="authors.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'AuthorView', params: { authorId: authors.id } }">{{ authors.name }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="book.id" :to="{ name: 'BookEdit', params: { bookId: book.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./book-details.component.ts"></script>

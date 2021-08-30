<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('myAppAgilekipCommunityApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('myAppAgilekipCommunityApp.taskSelectPickUpStore.number')"
                for="task-select-pick-up-store-number"
                >Number</label
              >
              <input
                type="text"
                class="form-control"
                name="number"
                id="task-select-pick-up-store-number"
                data-cy="number"
                :class="{
                  valid: !$v.taskContext.orderBookProcess.order.number.$invalid,
                  invalid: $v.taskContext.orderBookProcess.order.number.$invalid,
                }"
                v-model="$v.taskContext.orderBookProcess.order.number.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('myAppAgilekipCommunityApp.taskSelectPickUpStore.store')"
                for="task-select-pick-up-store-store"
                >Store</label
              >
              <select
                class="form-control"
                id="task-select-pick-up-store-store"
                data-cy="store"
                name="store"
                v-model="taskContext.orderBookProcess.order.store"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.orderBookProcess.order.store && storeOption.id === taskContext.orderBookProcess.order.store.id
                      ? taskContext.orderBookProcess.order.store
                      : storeOption
                  "
                  v-for="storeOption in stores"
                  :key="storeOption.id"
                >
                  {{ storeOption.name }}
                </option>
              </select>
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-select-pick-up-store-execute.component.ts"></script>

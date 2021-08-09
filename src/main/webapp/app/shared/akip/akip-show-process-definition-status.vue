<template>
  <div>
    <span class="badge rounded-pill bg-active" v-if="status == 'ACTIVE'">{{ status }}</span>
    <span class="badge rounded-pill bg-inactive" v-else-if="status == 'INACTIVE'">{{ status }}</span>
    <span class="badge rounded-pill bg-error" v-else-if="status == 'ERROR'">{{ status }}</span>
    <span class="badge rounded-pill bg-new" v-else-if="status == 'NEW'">{{ status }}</span>
  </div>
</template>

<script lang="ts" src="./akip-show-process-definition-status.component.ts"></script>

<style scoped>
.badge {
  color: white;
  padding: 0.35em 0.65em;
  font-weight: bold;
  font-size: 90%;
}

.bg-active,
.bg-new {
  color: white;
  background-color: green;
}

.bg-inactive {
  color: white;
  background-color: #586069;
}

.bg-error {
  color: white;
  background-color: darkred;
}
</style>

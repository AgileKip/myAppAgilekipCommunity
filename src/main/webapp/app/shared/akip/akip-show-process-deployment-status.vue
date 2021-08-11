<template>
  <div>
    <span class="badge rounded-pill bg-active" v-if="status == 'ACTIVE'">Active</span>
    <span class="badge rounded-pill bg-inactive" v-else-if="status == 'INACTIVE'">Inactive</span>
    <span class="badge rounded-pill bg-error" v-else-if="status == 'ERROR'">Error</span>
  </div>
</template>

<script lang="ts" src="./akip-show-process-deployment-status.component.ts"></script>

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

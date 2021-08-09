<template>
  <div>
    <span class="badge rounded-pill bg-running" v-if="status == 'RUNNING'">Running</span>
    <span class="badge rounded-pill bg-completed" v-else-if="status == 'COMPLETED'">Completed</span>
    <span class="badge rounded-pill bg-terminated" v-else-if="status == 'TERMINATED'">Terminated</span>
    <span class="badge rounded-pill bg-canceled" v-else-if="status == 'CANCELED'">Canceled</span>
  </div>
</template>

<script lang="ts" src="./akip-show-process-instance-status.component.ts"></script>

<style scoped>
.badge {
  color: white;
  padding: 0.35em 0.65em;
  font-weight: bold;
  font-size: 90%;
}

.bg-running {
  color: white;
  background-color: green;
}

.bg-completed,
.bg-terminated,
.bg-canceled {
  color: white;
  background-color: #586069;
}
</style>

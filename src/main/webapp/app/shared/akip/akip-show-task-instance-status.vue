<template>
  <div>
    <span class="badge rounded-pill bg-new" v-if="status == 'NEW'">New</span>
    <span class="badge rounded-pill bg-assigned" v-else-if="status == 'ASSIGNED'">Assigned</span>
    <span class="badge rounded-pill bg-completed" v-else-if="status == 'COMPLETED'">Completed</span>
    <span class="badge rounded-pill bg-unassigned" v-else-if="status == 'UNASSIGNED'">Unassigned</span>
    <span class="badge rounded-pill bg-delegated" v-else-if="status == 'DELEGATED'">Delegated</span>
    <span class="badge rounded-pill bg-delegated" v-else>None</span>
  </div>
</template>

<script lang="ts" src="./akip-show-task-instance-status.component.ts"></script>

<style scoped>
.badge {
  color: white;
  padding: 0.35em 0.65em;
  font-weight: bold;
  font-size: 90%;
}

.bg-new {
  color: white;
  background-color: green;
}

.bg-assigned {
  color: white;
  background-color: darkgreen;
}

.bg-completed {
  color: white;
  background-color: #586069;
}

.bg-unassigned {
  color: white;
  background-color: #3e8acc;
}

.bg-delegated {
  color: white;
  background-color: darkred;
}
</style>

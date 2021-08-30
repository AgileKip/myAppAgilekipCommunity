import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskAddBookService from './task-add-book.service';
import { TaskAddBookContext } from './task-add-book.model';

@Component
export default class TaskAddBookDetailsComponent extends Vue {
  private taskAddBookService: TaskAddBookService = new TaskAddBookService();
  private taskContext: TaskAddBookContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskAddBookService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

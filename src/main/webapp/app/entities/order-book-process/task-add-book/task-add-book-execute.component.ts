import { Component, Vue, Inject } from 'vue-property-decorator';

import BookService from '@/entities/book/book.service';
import { IBook } from '@/shared/model/book.model';

import TaskAddBookService from './task-add-book.service';
import { TaskAddBookContext } from './task-add-book.model';

const validations: any = {
  taskContext: {
    orderBookProcess: {
      order: {
        number: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskAddBookExecuteComponent extends Vue {
  private taskAddBookService: TaskAddBookService = new TaskAddBookService();
  private taskContext: TaskAddBookContext = {};

  @Inject('bookService') private bookService: () => BookService;

  public books: IBook[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskAddBookService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskAddBookService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.bookService()
      .retrieve()
      .then(res => {
        this.books = res.data;
      });
  }
}

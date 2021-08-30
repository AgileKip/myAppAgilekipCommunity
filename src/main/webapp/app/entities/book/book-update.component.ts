import { Component, Vue, Inject } from 'vue-property-decorator';

import PublisherService from '@/entities/publisher/publisher.service';
import { IPublisher } from '@/shared/model/publisher.model';

import AuthorService from '@/entities/author/author.service';
import { IAuthor } from '@/shared/model/author.model';

import OrderBookService from '@/entities/order-book/order-book.service';
import { IOrderBook } from '@/shared/model/order-book.model';

import { IBook, Book } from '@/shared/model/book.model';
import BookService from './book.service';

const validations: any = {
  book: {
    title: {},
    pubYear: {},
    price: {},
  },
};

@Component({
  validations,
})
export default class BookUpdate extends Vue {
  @Inject('bookService') private bookService: () => BookService;
  public book: IBook = new Book();

  @Inject('publisherService') private publisherService: () => PublisherService;

  public publishers: IPublisher[] = [];

  @Inject('authorService') private authorService: () => AuthorService;

  public authors: IAuthor[] = [];

  @Inject('orderBookService') private orderBookService: () => OrderBookService;

  public orderBooks: IOrderBook[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.bookId) {
        vm.retrieveBook(to.params.bookId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
    this.book.authors = [];
  }

  public save(): void {
    this.isSaving = true;
    if (this.book.id) {
      this.bookService()
        .update(this.book)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('myAppAgilekipCommunityApp.book.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.bookService()
        .create(this.book)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('myAppAgilekipCommunityApp.book.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveBook(bookId): void {
    this.bookService()
      .find(bookId)
      .then(res => {
        this.book = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.publisherService()
      .retrieve()
      .then(res => {
        this.publishers = res.data;
      });
    this.authorService()
      .retrieve()
      .then(res => {
        this.authors = res.data;
      });
    this.orderBookService()
      .retrieve()
      .then(res => {
        this.orderBooks = res.data;
      });
  }

  public getSelected(selectedVals, option): any {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}

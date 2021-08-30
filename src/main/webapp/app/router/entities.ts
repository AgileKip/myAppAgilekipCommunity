import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Author = () => import('@/entities/author/author.vue');
// prettier-ignore
const AuthorUpdate = () => import('@/entities/author/author-update.vue');
// prettier-ignore
const AuthorDetails = () => import('@/entities/author/author-details.vue');
// prettier-ignore
const Book = () => import('@/entities/book/book.vue');
// prettier-ignore
const BookUpdate = () => import('@/entities/book/book-update.vue');
// prettier-ignore
const BookDetails = () => import('@/entities/book/book-details.vue');
// prettier-ignore
const Order = () => import('@/entities/order/order.vue');
// prettier-ignore
const OrderUpdate = () => import('@/entities/order/order-update.vue');
// prettier-ignore
const OrderDetails = () => import('@/entities/order/order-details.vue');
// prettier-ignore
const OrderBook = () => import('@/entities/order-book/order-book.vue');
// prettier-ignore
const OrderBookUpdate = () => import('@/entities/order-book/order-book-update.vue');
// prettier-ignore
const OrderBookDetails = () => import('@/entities/order-book/order-book-details.vue');
// prettier-ignore
const Publisher = () => import('@/entities/publisher/publisher.vue');
// prettier-ignore
const PublisherUpdate = () => import('@/entities/publisher/publisher-update.vue');
// prettier-ignore
const PublisherDetails = () => import('@/entities/publisher/publisher-details.vue');
// prettier-ignore
const Store = () => import('@/entities/store/store.vue');
// prettier-ignore
const StoreUpdate = () => import('@/entities/store/store-update.vue');
// prettier-ignore
const StoreDetails = () => import('@/entities/store/store-details.vue');
// prettier-ignore
const OrderBookProcessDetails = () => import('@/entities/order-book-process/order-book-process-details.vue');
// prettier-ignore
const OrderBookProcessList = () => import('@/entities/order-book-process/order-book-process-list.vue');
// prettier-ignore
const OrderBookProcessStartFormInit = () => import('@/entities/order-book-process/order-book-process-start-form-init.vue');
// prettier-ignore
const OrderBookProcess_TaskAddBookDetails = () => import('@/entities/order-book-process/task-add-book/task-add-book-details.vue');
// prettier-ignore
const OrderBookProcess_TaskAddBookExecute = () => import('@/entities/order-book-process/task-add-book/task-add-book-execute.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/author',
    name: 'Author',
    component: Author,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/author/new',
    name: 'AuthorCreate',
    component: AuthorUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/author/:authorId/edit',
    name: 'AuthorEdit',
    component: AuthorUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/author/:authorId/view',
    name: 'AuthorView',
    component: AuthorDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/book',
    name: 'Book',
    component: Book,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/book/new',
    name: 'BookCreate',
    component: BookUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/book/:bookId/edit',
    name: 'BookEdit',
    component: BookUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/book/:bookId/view',
    name: 'BookView',
    component: BookDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/order',
    name: 'Order',
    component: Order,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/order/new',
    name: 'OrderCreate',
    component: OrderUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/order/:orderId/edit',
    name: 'OrderEdit',
    component: OrderUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/order/:orderId/view',
    name: 'OrderView',
    component: OrderDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/order-book',
    name: 'OrderBook',
    component: OrderBook,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/order-book/new',
    name: 'OrderBookCreate',
    component: OrderBookUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/order-book/:orderBookId/edit',
    name: 'OrderBookEdit',
    component: OrderBookUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/order-book/:orderBookId/view',
    name: 'OrderBookView',
    component: OrderBookDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/publisher',
    name: 'Publisher',
    component: Publisher,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/publisher/new',
    name: 'PublisherCreate',
    component: PublisherUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/publisher/:publisherId/edit',
    name: 'PublisherEdit',
    component: PublisherUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/publisher/:publisherId/view',
    name: 'PublisherView',
    component: PublisherDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/store',
    name: 'Store',
    component: Store,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/store/new',
    name: 'StoreCreate',
    component: StoreUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/store/:storeId/edit',
    name: 'StoreEdit',
    component: StoreUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/store/:storeId/view',
    name: 'StoreView',
    component: StoreDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/OrderBookProcess/instance/:processInstanceId/view',
    name: 'OrderBookProcessView',
    component: OrderBookProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/OrderBookProcess/instances',
    name: 'OrderBookProcessList',
    component: OrderBookProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/OrderBookProcess/init',
    name: 'OrderBookProcessStartFormInit',
    component: OrderBookProcessStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/OrderBookProcess/task/TaskAddBook/:taskInstanceId/view',
    name: 'OrderBookProcess_TaskAddBookDetails',
    component: OrderBookProcess_TaskAddBookDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/OrderBookProcess/task/TaskAddBook/:taskInstanceId/execute',
    name: 'OrderBookProcess_TaskAddBookExecute',
    component: OrderBookProcess_TaskAddBookExecute,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];

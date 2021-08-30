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
// prettier-ignore
const OrderBookProcess_TaskSelectDeliveryDetails = () => import('@/entities/order-book-process/task-select-delivery/task-select-delivery-details.vue');
// prettier-ignore
const OrderBookProcess_TaskSelectDeliveryExecute = () => import('@/entities/order-book-process/task-select-delivery/task-select-delivery-execute.vue');
// prettier-ignore
const OrderBookProcess_TaskAddShippingInfoDetails = () => import('@/entities/order-book-process/task-add-shipping-info/task-add-shipping-info-details.vue');
// prettier-ignore
const OrderBookProcess_TaskAddShippingInfoExecute = () => import('@/entities/order-book-process/task-add-shipping-info/task-add-shipping-info-execute.vue');
// prettier-ignore
const OrderBookProcess_TaskSelectPickUpStoreDetails = () => import('@/entities/order-book-process/task-select-pick-up-store/task-select-pick-up-store-details.vue');
// prettier-ignore
const OrderBookProcess_TaskSelectPickUpStoreExecute = () => import('@/entities/order-book-process/task-select-pick-up-store/task-select-pick-up-store-execute.vue');
// prettier-ignore
const OrderBookProcess_TaskPayBookDetails = () => import('@/entities/order-book-process/task-pay-book/task-pay-book-details.vue');
// prettier-ignore
const OrderBookProcess_TaskPayBookExecute = () => import('@/entities/order-book-process/task-pay-book/task-pay-book-execute.vue');
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
  {
    path: '/process-definition/OrderBookProcess/task/TaskSelectDelivery/:taskInstanceId/view',
    name: 'OrderBookProcess_TaskSelectDeliveryDetails',
    component: OrderBookProcess_TaskSelectDeliveryDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/OrderBookProcess/task/TaskSelectDelivery/:taskInstanceId/execute',
    name: 'OrderBookProcess_TaskSelectDeliveryExecute',
    component: OrderBookProcess_TaskSelectDeliveryExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/OrderBookProcess/task/TaskAddShippingInfo/:taskInstanceId/view',
    name: 'OrderBookProcess_TaskAddShippingInfoDetails',
    component: OrderBookProcess_TaskAddShippingInfoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/OrderBookProcess/task/TaskAddShippingInfo/:taskInstanceId/execute',
    name: 'OrderBookProcess_TaskAddShippingInfoExecute',
    component: OrderBookProcess_TaskAddShippingInfoExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/OrderBookProcess/task/TaskSelectPickUpStore/:taskInstanceId/view',
    name: 'OrderBookProcess_TaskSelectPickUpStoreDetails',
    component: OrderBookProcess_TaskSelectPickUpStoreDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/OrderBookProcess/task/TaskSelectPickUpStore/:taskInstanceId/execute',
    name: 'OrderBookProcess_TaskSelectPickUpStoreExecute',
    component: OrderBookProcess_TaskSelectPickUpStoreExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/OrderBookProcess/task/TaskPayBook/:taskInstanceId/view',
    name: 'OrderBookProcess_TaskPayBookDetails',
    component: OrderBookProcess_TaskPayBookDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/OrderBookProcess/task/TaskPayBook/:taskInstanceId/execute',
    name: 'OrderBookProcess_TaskPayBookExecute',
    component: OrderBookProcess_TaskPayBookExecute,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];

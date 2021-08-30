/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import OrderBookUpdateComponent from '@/entities/order-book/order-book-update.vue';
import OrderBookClass from '@/entities/order-book/order-book-update.component';
import OrderBookService from '@/entities/order-book/order-book.service';

import OrderService from '@/entities/order/order.service';

import BookService from '@/entities/book/book.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('OrderBook Management Update Component', () => {
    let wrapper: Wrapper<OrderBookClass>;
    let comp: OrderBookClass;
    let orderBookServiceStub: SinonStubbedInstance<OrderBookService>;

    beforeEach(() => {
      orderBookServiceStub = sinon.createStubInstance<OrderBookService>(OrderBookService);

      wrapper = shallowMount<OrderBookClass>(OrderBookUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          orderBookService: () => orderBookServiceStub,

          orderService: () => new OrderService(),

          bookService: () => new BookService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.orderBook = entity;
        orderBookServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(orderBookServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.orderBook = entity;
        orderBookServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(orderBookServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundOrderBook = { id: 123 };
        orderBookServiceStub.find.resolves(foundOrderBook);
        orderBookServiceStub.retrieve.resolves([foundOrderBook]);

        // WHEN
        comp.beforeRouteEnter({ params: { orderBookId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.orderBook).toBe(foundOrderBook);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});

/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import OrderBookDetailComponent from '@/entities/order-book/order-book-details.vue';
import OrderBookClass from '@/entities/order-book/order-book-details.component';
import OrderBookService from '@/entities/order-book/order-book.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('OrderBook Management Detail Component', () => {
    let wrapper: Wrapper<OrderBookClass>;
    let comp: OrderBookClass;
    let orderBookServiceStub: SinonStubbedInstance<OrderBookService>;

    beforeEach(() => {
      orderBookServiceStub = sinon.createStubInstance<OrderBookService>(OrderBookService);

      wrapper = shallowMount<OrderBookClass>(OrderBookDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { orderBookService: () => orderBookServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundOrderBook = { id: 123 };
        orderBookServiceStub.find.resolves(foundOrderBook);

        // WHEN
        comp.retrieveOrderBook(123);
        await comp.$nextTick();

        // THEN
        expect(comp.orderBook).toBe(foundOrderBook);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundOrderBook = { id: 123 };
        orderBookServiceStub.find.resolves(foundOrderBook);

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

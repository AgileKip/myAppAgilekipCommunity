/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import OrderBookComponent from '@/entities/order-book/order-book.vue';
import OrderBookClass from '@/entities/order-book/order-book.component';
import OrderBookService from '@/entities/order-book/order-book.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('OrderBook Management Component', () => {
    let wrapper: Wrapper<OrderBookClass>;
    let comp: OrderBookClass;
    let orderBookServiceStub: SinonStubbedInstance<OrderBookService>;

    beforeEach(() => {
      orderBookServiceStub = sinon.createStubInstance<OrderBookService>(OrderBookService);
      orderBookServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<OrderBookClass>(OrderBookComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          orderBookService: () => orderBookServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      orderBookServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllOrderBooks();
      await comp.$nextTick();

      // THEN
      expect(orderBookServiceStub.retrieve.called).toBeTruthy();
      expect(comp.orderBooks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      orderBookServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeOrderBook();
      await comp.$nextTick();

      // THEN
      expect(orderBookServiceStub.delete.called).toBeTruthy();
      expect(orderBookServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});

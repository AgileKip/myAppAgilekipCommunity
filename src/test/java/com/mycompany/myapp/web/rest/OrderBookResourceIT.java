package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.OrderBook;
import com.mycompany.myapp.repository.OrderBookRepository;
import com.mycompany.myapp.service.dto.OrderBookDTO;
import com.mycompany.myapp.service.mapper.OrderBookMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link OrderBookResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OrderBookResourceIT {

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final String ENTITY_API_URL = "/api/order-books";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OrderBookRepository orderBookRepository;

    @Autowired
    private OrderBookMapper orderBookMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderBookMockMvc;

    private OrderBook orderBook;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderBook createEntity(EntityManager em) {
        OrderBook orderBook = new OrderBook().quantity(DEFAULT_QUANTITY);
        return orderBook;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderBook createUpdatedEntity(EntityManager em) {
        OrderBook orderBook = new OrderBook().quantity(UPDATED_QUANTITY);
        return orderBook;
    }

    @BeforeEach
    public void initTest() {
        orderBook = createEntity(em);
    }

    @Test
    @Transactional
    void createOrderBook() throws Exception {
        int databaseSizeBeforeCreate = orderBookRepository.findAll().size();
        // Create the OrderBook
        OrderBookDTO orderBookDTO = orderBookMapper.toDto(orderBook);
        restOrderBookMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderBookDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderBook in the database
        List<OrderBook> orderBookList = orderBookRepository.findAll();
        assertThat(orderBookList).hasSize(databaseSizeBeforeCreate + 1);
        OrderBook testOrderBook = orderBookList.get(orderBookList.size() - 1);
        assertThat(testOrderBook.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    void createOrderBookWithExistingId() throws Exception {
        // Create the OrderBook with an existing ID
        orderBook.setId(1L);
        OrderBookDTO orderBookDTO = orderBookMapper.toDto(orderBook);

        int databaseSizeBeforeCreate = orderBookRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderBookMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderBookDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderBook in the database
        List<OrderBook> orderBookList = orderBookRepository.findAll();
        assertThat(orderBookList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderBookRepository.findAll().size();
        // set the field null
        orderBook.setQuantity(null);

        // Create the OrderBook, which fails.
        OrderBookDTO orderBookDTO = orderBookMapper.toDto(orderBook);

        restOrderBookMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderBookDTO)))
            .andExpect(status().isBadRequest());

        List<OrderBook> orderBookList = orderBookRepository.findAll();
        assertThat(orderBookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllOrderBooks() throws Exception {
        // Initialize the database
        orderBookRepository.saveAndFlush(orderBook);

        // Get all the orderBookList
        restOrderBookMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderBook.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)));
    }

    @Test
    @Transactional
    void getOrderBook() throws Exception {
        // Initialize the database
        orderBookRepository.saveAndFlush(orderBook);

        // Get the orderBook
        restOrderBookMockMvc
            .perform(get(ENTITY_API_URL_ID, orderBook.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderBook.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY));
    }

    @Test
    @Transactional
    void getNonExistingOrderBook() throws Exception {
        // Get the orderBook
        restOrderBookMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewOrderBook() throws Exception {
        // Initialize the database
        orderBookRepository.saveAndFlush(orderBook);

        int databaseSizeBeforeUpdate = orderBookRepository.findAll().size();

        // Update the orderBook
        OrderBook updatedOrderBook = orderBookRepository.findById(orderBook.getId()).get();
        // Disconnect from session so that the updates on updatedOrderBook are not directly saved in db
        em.detach(updatedOrderBook);
        updatedOrderBook.quantity(UPDATED_QUANTITY);
        OrderBookDTO orderBookDTO = orderBookMapper.toDto(updatedOrderBook);

        restOrderBookMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orderBookDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orderBookDTO))
            )
            .andExpect(status().isOk());

        // Validate the OrderBook in the database
        List<OrderBook> orderBookList = orderBookRepository.findAll();
        assertThat(orderBookList).hasSize(databaseSizeBeforeUpdate);
        OrderBook testOrderBook = orderBookList.get(orderBookList.size() - 1);
        assertThat(testOrderBook.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    void putNonExistingOrderBook() throws Exception {
        int databaseSizeBeforeUpdate = orderBookRepository.findAll().size();
        orderBook.setId(count.incrementAndGet());

        // Create the OrderBook
        OrderBookDTO orderBookDTO = orderBookMapper.toDto(orderBook);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderBookMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orderBookDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orderBookDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderBook in the database
        List<OrderBook> orderBookList = orderBookRepository.findAll();
        assertThat(orderBookList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOrderBook() throws Exception {
        int databaseSizeBeforeUpdate = orderBookRepository.findAll().size();
        orderBook.setId(count.incrementAndGet());

        // Create the OrderBook
        OrderBookDTO orderBookDTO = orderBookMapper.toDto(orderBook);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderBookMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orderBookDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderBook in the database
        List<OrderBook> orderBookList = orderBookRepository.findAll();
        assertThat(orderBookList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOrderBook() throws Exception {
        int databaseSizeBeforeUpdate = orderBookRepository.findAll().size();
        orderBook.setId(count.incrementAndGet());

        // Create the OrderBook
        OrderBookDTO orderBookDTO = orderBookMapper.toDto(orderBook);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderBookMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderBookDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderBook in the database
        List<OrderBook> orderBookList = orderBookRepository.findAll();
        assertThat(orderBookList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOrderBookWithPatch() throws Exception {
        // Initialize the database
        orderBookRepository.saveAndFlush(orderBook);

        int databaseSizeBeforeUpdate = orderBookRepository.findAll().size();

        // Update the orderBook using partial update
        OrderBook partialUpdatedOrderBook = new OrderBook();
        partialUpdatedOrderBook.setId(orderBook.getId());

        restOrderBookMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderBook.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrderBook))
            )
            .andExpect(status().isOk());

        // Validate the OrderBook in the database
        List<OrderBook> orderBookList = orderBookRepository.findAll();
        assertThat(orderBookList).hasSize(databaseSizeBeforeUpdate);
        OrderBook testOrderBook = orderBookList.get(orderBookList.size() - 1);
        assertThat(testOrderBook.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    void fullUpdateOrderBookWithPatch() throws Exception {
        // Initialize the database
        orderBookRepository.saveAndFlush(orderBook);

        int databaseSizeBeforeUpdate = orderBookRepository.findAll().size();

        // Update the orderBook using partial update
        OrderBook partialUpdatedOrderBook = new OrderBook();
        partialUpdatedOrderBook.setId(orderBook.getId());

        partialUpdatedOrderBook.quantity(UPDATED_QUANTITY);

        restOrderBookMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderBook.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrderBook))
            )
            .andExpect(status().isOk());

        // Validate the OrderBook in the database
        List<OrderBook> orderBookList = orderBookRepository.findAll();
        assertThat(orderBookList).hasSize(databaseSizeBeforeUpdate);
        OrderBook testOrderBook = orderBookList.get(orderBookList.size() - 1);
        assertThat(testOrderBook.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    void patchNonExistingOrderBook() throws Exception {
        int databaseSizeBeforeUpdate = orderBookRepository.findAll().size();
        orderBook.setId(count.incrementAndGet());

        // Create the OrderBook
        OrderBookDTO orderBookDTO = orderBookMapper.toDto(orderBook);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderBookMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, orderBookDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(orderBookDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderBook in the database
        List<OrderBook> orderBookList = orderBookRepository.findAll();
        assertThat(orderBookList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOrderBook() throws Exception {
        int databaseSizeBeforeUpdate = orderBookRepository.findAll().size();
        orderBook.setId(count.incrementAndGet());

        // Create the OrderBook
        OrderBookDTO orderBookDTO = orderBookMapper.toDto(orderBook);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderBookMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(orderBookDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderBook in the database
        List<OrderBook> orderBookList = orderBookRepository.findAll();
        assertThat(orderBookList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOrderBook() throws Exception {
        int databaseSizeBeforeUpdate = orderBookRepository.findAll().size();
        orderBook.setId(count.incrementAndGet());

        // Create the OrderBook
        OrderBookDTO orderBookDTO = orderBookMapper.toDto(orderBook);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderBookMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(orderBookDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderBook in the database
        List<OrderBook> orderBookList = orderBookRepository.findAll();
        assertThat(orderBookList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOrderBook() throws Exception {
        // Initialize the database
        orderBookRepository.saveAndFlush(orderBook);

        int databaseSizeBeforeDelete = orderBookRepository.findAll().size();

        // Delete the orderBook
        restOrderBookMockMvc
            .perform(delete(ENTITY_API_URL_ID, orderBook.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderBook> orderBookList = orderBookRepository.findAll();
        assertThat(orderBookList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

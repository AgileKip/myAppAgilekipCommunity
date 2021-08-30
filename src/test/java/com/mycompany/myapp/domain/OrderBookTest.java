package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrderBookTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderBook.class);
        OrderBook orderBook1 = new OrderBook();
        orderBook1.setId(1L);
        OrderBook orderBook2 = new OrderBook();
        orderBook2.setId(orderBook1.getId());
        assertThat(orderBook1).isEqualTo(orderBook2);
        orderBook2.setId(2L);
        assertThat(orderBook1).isNotEqualTo(orderBook2);
        orderBook1.setId(null);
        assertThat(orderBook1).isNotEqualTo(orderBook2);
    }
}

package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrderBookDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderBookDTO.class);
        OrderBookDTO orderBookDTO1 = new OrderBookDTO();
        orderBookDTO1.setId(1L);
        OrderBookDTO orderBookDTO2 = new OrderBookDTO();
        assertThat(orderBookDTO1).isNotEqualTo(orderBookDTO2);
        orderBookDTO2.setId(orderBookDTO1.getId());
        assertThat(orderBookDTO1).isEqualTo(orderBookDTO2);
        orderBookDTO2.setId(2L);
        assertThat(orderBookDTO1).isNotEqualTo(orderBookDTO2);
        orderBookDTO1.setId(null);
        assertThat(orderBookDTO1).isNotEqualTo(orderBookDTO2);
    }
}

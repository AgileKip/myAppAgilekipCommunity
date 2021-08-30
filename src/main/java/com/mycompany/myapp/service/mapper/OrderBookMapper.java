package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.OrderBookDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderBook} and its DTO {@link OrderBookDTO}.
 */
@Mapper(componentModel = "spring", uses = { OrderMapper.class, BookMapper.class })
public interface OrderBookMapper extends EntityMapper<OrderBookDTO, OrderBook> {
    @Mapping(target = "order", source = "order", qualifiedByName = "id")
    @Mapping(target = "book", source = "book", qualifiedByName = "id")
    OrderBookDTO toDto(OrderBook s);
}

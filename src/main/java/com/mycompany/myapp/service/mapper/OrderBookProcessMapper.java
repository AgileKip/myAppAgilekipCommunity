package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.OrderBookProcessDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderBookProcess} and its DTO {@link OrderBookProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, OrderMapper.class })
public interface OrderBookProcessMapper extends EntityMapper<OrderBookProcessDTO, OrderBookProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "order", source = "order")
    OrderBookProcessDTO toDto(OrderBookProcess s);
}

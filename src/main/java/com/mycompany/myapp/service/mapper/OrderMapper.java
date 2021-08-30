package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.OrderDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Order} and its DTO {@link OrderDTO}.
 */
@Mapper(componentModel = "spring", uses = { BookMapper.class, UserMapper.class, StoreMapper.class })
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {
    @Mapping(target = "book", source = "book", qualifiedByName = "title")
    @Mapping(target = "user", source = "user", qualifiedByName = "login")
    @Mapping(target = "store", source = "store", qualifiedByName = "name")
    OrderDTO toDto(Order s);
}

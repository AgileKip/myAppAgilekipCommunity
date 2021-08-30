package com.mycompany.myapp.process.orderBookProcess;

import com.mycompany.myapp.domain.Order;
import com.mycompany.myapp.domain.OrderBookProcess;
import com.mycompany.myapp.domain.Store;
import com.mycompany.myapp.service.dto.OrderBookProcessDTO;
import com.mycompany.myapp.service.dto.OrderDTO;
import com.mycompany.myapp.service.dto.StoreDTO;
import com.mycompany.myapp.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskSelectPickUpStoreMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    OrderBookProcessDTO toOrderBookProcessDTO(OrderBookProcess orderBookProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "store", source = "store")
    OrderDTO toOrderDTO(Order order);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    StoreDTO toStoreDTO(Store name);
}

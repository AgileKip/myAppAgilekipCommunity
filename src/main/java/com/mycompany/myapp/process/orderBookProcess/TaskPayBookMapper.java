package com.mycompany.myapp.process.orderBookProcess;

import com.mycompany.myapp.domain.Order;
import com.mycompany.myapp.domain.OrderBookProcess;
import com.mycompany.myapp.service.dto.OrderBookProcessDTO;
import com.mycompany.myapp.service.dto.OrderDTO;
import com.mycompany.myapp.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskPayBookMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    OrderBookProcessDTO toOrderBookProcessDTO(OrderBookProcess orderBookProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "total", source = "total")
    @Mapping(target = "ccNumber", source = "ccNumber")
    @Mapping(target = "ccDate", source = "ccDate")
    @Mapping(target = "ccVerifierDigit", source = "ccVerifierDigit")
    OrderDTO toOrderDTO(Order order);
}

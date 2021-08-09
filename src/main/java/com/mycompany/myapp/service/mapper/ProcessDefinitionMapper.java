package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ProcessDefinitionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProcessDefinition} and its DTO {@link ProcessDefinitionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProcessDefinitionMapper extends EntityMapper<ProcessDefinitionDTO, ProcessDefinition> {
    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "bpmnProcessDefinitionId", source = "bpmnProcessDefinitionId")
    ProcessDefinitionDTO toDtoName(ProcessDefinition processDefinition);

    @Named("loadTaskContext")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "bpmnProcessDefinitionId", source = "bpmnProcessDefinitionId")
    ProcessDefinitionDTO toDTOLoadTaskContext(ProcessDefinition processDefinition);
}

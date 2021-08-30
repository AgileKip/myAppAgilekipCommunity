package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.AuthorDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Author} and its DTO {@link AuthorDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuthorMapper extends EntityMapper<AuthorDTO, Author> {
    @Named("nameSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    Set<AuthorDTO> toDtoNameSet(Set<Author> author);
}

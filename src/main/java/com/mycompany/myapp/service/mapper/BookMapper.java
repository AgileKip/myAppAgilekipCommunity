package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.BookDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Book} and its DTO {@link BookDTO}.
 */
@Mapper(componentModel = "spring", uses = { PublisherMapper.class, AuthorMapper.class })
public interface BookMapper extends EntityMapper<BookDTO, Book> {
    @Mapping(target = "publisher", source = "publisher", qualifiedByName = "name")
    @Mapping(target = "authors", source = "authors", qualifiedByName = "nameSet")
    BookDTO toDto(Book s);

    @Mapping(target = "removeAuthors", ignore = true)
    Book toEntity(BookDTO bookDTO);

    @Named("title")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    BookDTO toDtoTitle(Book book);
}

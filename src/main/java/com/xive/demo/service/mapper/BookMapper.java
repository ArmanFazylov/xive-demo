package com.xive.demo.service.mapper;

import com.xive.demo.dto.BookDTO;
import com.xive.demo.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Created by Arman on Feb  06, 2020
 * Feel free to email me: arman.fazylov@gmail.com
 */

/**
 * Mapper for the entity {@link Book} and its DTO {@link BookDTO}.
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookMapper extends EntityMapper<BookDTO, Book> {
    default Book fromId(Long id) {
        if (id == null) {
            return null;
        }
        Book book = new Book();
        book.setId(id);
        return book;
    }
}

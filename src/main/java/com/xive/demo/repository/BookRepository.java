package com.xive.demo.repository;

import com.xive.demo.entity.Book;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Book entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends BaseRepository<Book> {

}

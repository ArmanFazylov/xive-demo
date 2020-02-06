package com.xive.demo.service.book;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import com.xive.demo.dto.BookDTO;
import com.xive.demo.entity.Book;
import com.xive.demo.repository.BookRepository;
import com.xive.demo.service.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Arman on Feb  06, 2020
 * Feel free to email me: arman.fazylov@gmail.com
 */

/**
 * Service Implementation for managing {@link Book}.
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @PostConstruct
    public void populateWithRandomBooks() {
        Lorem loremIpsum = LoremIpsum.getInstance();
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Book book = new Book();
            String title = loremIpsum.getTitle(3, 5);
            String description = loremIpsum.getParagraphs(1, 2);
            String paragraphs = loremIpsum.getParagraphs(50, 50);
            book.setTitle(title);
            book.setDescription(description);
            book.setFirst50Paragraphs(paragraphs);
            books.add(book);
        }
        bookRepository.saveAll(books);
    }

    /**
     * Save a book.
     *
     * @param bookDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BookDTO save(BookDTO bookDTO) {
        log.debug("Request to save Book : {}", bookDTO);
        Book book = bookMapper.toEntity(bookDTO);
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    /**
     * Get all the books.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BookDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Books");
        return bookRepository.findAll(pageable).map(bookMapper::toDto);
    }


    /**
     * Get one book by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BookDTO> findOne(Long id) {
        log.debug("Request to get Book : {}", id);
        return bookRepository.findById(id).map(bookMapper::toDto);
    }

    /**
     * Delete the book by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Book : {}", id);
        bookRepository.deleteById(id);
    }
}

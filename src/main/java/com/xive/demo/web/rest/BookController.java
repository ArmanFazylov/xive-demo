package com.xive.demo.web.rest;

import com.xive.demo.dto.BookDTO;
import com.xive.demo.service.auth.IAuthenticationFacade;
import com.xive.demo.service.book.BookService;
import com.xive.demo.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Arman on Feb  06, 2020
 * Feel free to email me: arman.fazylov@gmail.com
 */

@RestController
@RequestMapping(value = "/api/v1/books", produces = "application/json")
@ResponseBody
@Api(value = "Rest api for managing books", tags = "Books API")
@Slf4j
@RequiredArgsConstructor
public class BookController {
    private final IAuthenticationFacade authenticationFacade;
    private final BookService bookService;
    private static final String ENTITY_NAME = "book";
    private static final String APPLICATION_NAME = "XIVE-demo";

    /**
     * {@code POST  /books} : Create a new book.
     *
     * @param bookDTO the bookDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bookDTO, or with status {@code 400 (Bad Request)} if the book has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) throws URISyntaxException {
        log.debug("REST request to save Book : {}", bookDTO);
        if (bookDTO.getId() != null) {
            throw new BadRequestAlertException("A new book cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BookDTO result = bookService.save(bookDTO);
        return ResponseEntity.created(new URI("/api/books/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(APPLICATION_NAME, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /books} : Updates an existing book.
     *
     * @param bookDTO the bookDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bookDTO,
     * or with status {@code 400 (Bad Request)} if the bookDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bookDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping
    public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody BookDTO bookDTO) throws URISyntaxException {
        log.debug("REST request to update Book : {}", bookDTO);
        if (bookDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BookDTO result = bookService.save(bookDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(APPLICATION_NAME, false, ENTITY_NAME, bookDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /books} : get all the books.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of books in body.
     */
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(Pageable pageable) {
        log.debug("REST request to get a page of Books");
        Page<BookDTO> page = bookService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /books/:id} : get the "id" book.
     *
     * @param id the id of the bookDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bookDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        log.debug("REST request to get Book : {}", id);
        Optional<BookDTO> bookDTO = bookService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bookDTO);
    }

    /**
     * {@code DELETE  /books/:id} : delete the "id" book.
     *
     * @param id the id of the bookDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.debug("REST request to delete Book : {}", id);
        bookService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(APPLICATION_NAME, false, ENTITY_NAME, id.toString())).build();
    }

}

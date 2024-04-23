package org.gr40in.library.provider;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.gr40in.library.dao.Book;
import org.gr40in.library.repository.BookRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookProvider {
    private final BookRepository bookRepository;

    private final String BASE_URI = "http://books/api/book";

    RestClient restClient = RestClient.create();

    public List<Book> getNewBooks() {
        List<Book> books = restClient.get()
                .uri(BASE_URI)
                .retrieve()
                .body(ParameterizedTypeReference.forType(TypeUtils.parameterize(List.class, Book.class)));

        if (!books.isEmpty()) bookRepository.saveAll(books);

        return books;
    }

    public Book getBookById(Long id) {
        Book book = restClient.get()
                .uri(BASE_URI + "/" + id)
                .retrieve()
                .body(Book.class);
        if (book == null) throw new NoSuchElementException();
        bookRepository.save(book);
        return book;
    }
}

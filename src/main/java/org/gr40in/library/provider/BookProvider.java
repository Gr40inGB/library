package org.gr40in.library.provider;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.gr40in.library.dao.Book;
import org.gr40in.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookProvider {
    private final BookRepository bookRepository;

    private final String BASE_URI = "http://books/api/book";
//    private final RestClient restClient;
    private final WebClient.Builder builder;

    public List<Book> getNewBooks() {

        List<Book> books =builder.build().get()
                .uri(BASE_URI)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Book>>() {})
                .block();

        if (!books.isEmpty()) bookRepository.saveAll(books);

        return books;
    }

    public Book getBookById(Long id) {
        Book blocked = builder.build().get()
                .uri(BASE_URI + "/" + id)
                .retrieve().bodyToMono(Book.class).block();

        if (blocked == null) throw new NoSuchElementException();
        bookRepository.save(blocked);
        return blocked;
    }
}

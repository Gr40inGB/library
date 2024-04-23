package org.gr40in.library.dto;

import lombok.RequiredArgsConstructor;
import org.gr40in.library.dao.Book;
import org.gr40in.library.dao.Client;
import org.gr40in.library.dao.Rental;
import org.gr40in.library.provider.BookProvider;
import org.gr40in.library.provider.ClientProvider;
import org.gr40in.library.repository.BookRepository;
import org.gr40in.library.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalMapper {

    private final BookProvider bookProvider;
    private final ClientProvider clientProvider;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;

    public Rental toEntity(RentalDto rentalDto) {

        var book = bookRepository.findById(rentalDto.getBook());
        var bookEntity = book.orElseGet(() -> bookProvider.getBookById(rentalDto.getBook()));
        var client = clientRepository.findById(rentalDto.getClient());
        var clientEntity = client.orElseGet(() -> clientProvider.getClientById(rentalDto.getClient()));
        if (book.isPresent() && client.isPresent())
            return Rental.builder()
                    .book(bookEntity)
                    .client(clientEntity)
                    .build();
        else throw new RuntimeException("Mapper brr");
    }

    public RentalDto toDto(Rental rental) {
        return RentalDto.builder()
                .id(rental.getId())
                .client(rental.getClient().getId())
                .book(rental.getBook().getId())
                .build();
    }

}

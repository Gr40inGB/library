package org.gr40in.library.controller;

import lombok.RequiredArgsConstructor;
import org.gr40in.library.dao.Book;
import org.gr40in.library.dao.Client;
import org.gr40in.library.dto.RentalDto;
import org.gr40in.library.provider.BookProvider;
import org.gr40in.library.provider.ClientProvider;
import org.gr40in.library.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rental")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    private final BookProvider bookProvider;
    private final ClientProvider clientProvider;

    @PostMapping("create")
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalDto rentalDto) {
        return ResponseEntity.ok().body(rentalService.createRental(rentalDto));
    }

    @GetMapping
    public ResponseEntity<List<RentalDto>> findAllRental() {
        return ResponseEntity.ok().body(rentalService.findAllRentals());
    }

    @GetMapping("books")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok().body(bookProvider.getNewBooks());
    }

    @GetMapping("clients")
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok().body(clientProvider.getClients());
    }





}

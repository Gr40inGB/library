package org.gr40in.library.service;

import lombok.RequiredArgsConstructor;
import org.gr40in.library.dao.Rental;
import org.gr40in.library.dto.RentalDto;
import org.gr40in.library.dto.RentalMapper;
import org.gr40in.library.provider.BookProvider;
import org.gr40in.library.provider.ClientProvider;
import org.gr40in.library.repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public List<RentalDto> findAllRentals() {
        return rentalRepository.findAll()
                .stream()
                .map(rentalMapper::toDto)
                .toList();
    }

    public RentalDto findRentalById(long id) {
        var rental = rentalRepository.findById(id);
        if (rental.isPresent()) return rentalMapper.toDto(rental.get());
        else throw new NoSuchElementException("cant find rental with id " + id);
    }

    public RentalDto createRental(RentalDto rentalDto) {
        rentalDto.setId(null);
        var rental = rentalRepository.save(rentalMapper.toEntity(rentalDto));
        return rentalMapper.toDto(rental);
    }

    public RentalDto updateRental(Long id, RentalDto rentalDto) {
        var rentalOptional = rentalRepository.findById(id);
        if (rentalOptional.isPresent()) {
            var newData = rentalMapper.toEntity(rentalDto);
            var rental = rentalOptional.get();
            rental.setBook(newData.getBook());
            rental.setClient(newData.getClient());
            return rentalMapper.toDto(rental);
        } else throw new NoSuchElementException("cant find rental with id " + rentalDto.getId());
    }

    public boolean deleteRental(Long id) {
        try {
            rentalRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

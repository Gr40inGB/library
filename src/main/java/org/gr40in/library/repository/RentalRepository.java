package org.gr40in.library.repository;

import org.gr40in.library.dao.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}

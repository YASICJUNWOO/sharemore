package com.kjw.sharemore.reservation.repository;

import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByItemAndStartDateGreaterThanEqualAndEndDateLessThanEqual(Item item, LocalDateTime startDate, LocalDateTime endDate);
}

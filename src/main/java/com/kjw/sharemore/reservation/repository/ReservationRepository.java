package com.kjw.sharemore.reservation.repository;

import com.kjw.sharemore.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

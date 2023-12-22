package com.kjw.sharemore.reservation.repository;

import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    /**
    * @methodName : findByItemAndStartDateGreaterThanEqualAndEndDateLessThanEqual
    * @param : 
    * @return : 
    * @Description: 해당 Item에 대해서 시간 사이의 예약이 탐색
    * @note:
    **/
    Optional<Reservation> findByItemAndStartDateGreaterThanEqualAndEndDateLessThanEqual(Item item, LocalDateTime startDate, LocalDateTime endDate);
}

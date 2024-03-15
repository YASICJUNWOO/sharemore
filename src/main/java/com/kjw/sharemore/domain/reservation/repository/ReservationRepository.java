package com.kjw.sharemore.domain.reservation.repository;

import com.kjw.sharemore.domain.item.normalItem.entity.Item;
import com.kjw.sharemore.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    /**
    * @methodName : findByItemAndStartDateGreaterThanEqualAndEndDateLessThanEqual
    * @param : 
    * @return : 
    * @Description: 해당 Item에 대해서 시간 사이의 예약이 탐색
    * @note:
    **/
    Optional<Reservation> findByItemAndStartDateLessThanEqualAndEndDateGreaterThan(Item item, LocalDateTime startDate, LocalDateTime startDate2);
    Optional<Reservation> findByItemAndStartDateGreaterThanAndStartDateLessThan(Item item, LocalDateTime startDate, LocalDateTime startDate2);

    Optional<Reservation> findFirstByItem_ItemIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long itemId, LocalDateTime startDate, LocalDateTime endDate);

    //해당 일자의 모든 예약을 가져옴 오를차순으로
    List<Reservation> findAllByItem_ItemIdAndStartDateLessThanEqualAndEndDateGreaterThan(Long itemId, LocalDateTime startDate, LocalDateTime endDate);

    List<Reservation> findAllByItem(Item itemByItemId);
}

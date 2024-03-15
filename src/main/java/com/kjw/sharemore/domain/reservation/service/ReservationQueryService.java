package com.kjw.sharemore.domain.reservation.service;

import com.kjw.sharemore.apiPayLoad.exception.handler.ReservationExceptionHandler;
import com.kjw.sharemore.domain.item.normalItem.entity.Item;
import com.kjw.sharemore.domain.item.normalItem.service.ItemQueryService;
import com.kjw.sharemore.domain.reservation.Reservation;
import com.kjw.sharemore.domain.reservation.dto.response.ReservationResponseDTO;
import com.kjw.sharemore.domain.reservation.dto.request.ReservationRequestDTO;
import com.kjw.sharemore.domain.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationQueryService {

    private final ReservationRepository reservationRepository;
    private final ItemQueryService itemQueryService;


    public List<ReservationResponseDTO.Simple> getReservationList(Long itemId) {
        Item itemByItemId = itemQueryService.getItemByItemId(itemId);
        return reservationRepository.findAllByItem(itemByItemId).stream().map(ReservationResponseDTO.Simple::of).toList();
    }


    /**
     * @Description: 해당 날짜의 예약 조회
     **/
    public List<ReservationResponseDTO.Simple> getReservationByIdAndDate(Long itemId, LocalDateTime date) {

        LocalDateTime startDate = date.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endDate = date.withHour(23).withMinute(59).withSecond(59);
        return reservationRepository
                .findAllByItem_ItemIdAndStartDateLessThanEqualAndEndDateGreaterThan(itemId, endDate, startDate)
                .stream()
                .map(ReservationResponseDTO.Simple::of)
                .toList();

    }

    /**
     * @Description: 중복 예약 확인
     **/
    public void validateDuplicateReservation(ReservationRequestDTO reservationRequestDTO) {

        Optional<Reservation> findByDate = reservationRepository.findFirstByItem_ItemIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                reservationRequestDTO.getItemId(), reservationRequestDTO.getStartDate(), reservationRequestDTO.getEndDate()
        );

        if (findByDate.isPresent()) {
            throw new ReservationExceptionHandler.DuplicateReservation();
        }

    }

}

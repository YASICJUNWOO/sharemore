package com.kjw.sharemore.reservation.service;

import com.kjw.sharemore.apiPayLoad.exception.handler.ReservationExceptionHandler;
import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.item.service.ItemService;
import com.kjw.sharemore.reservation.Reservation;
import com.kjw.sharemore.reservation.dto.request.ReservationRequestDTO;
import com.kjw.sharemore.reservation.dto.response.ReservationResponseDTO;
import com.kjw.sharemore.reservation.repository.ReservationRepository;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final ItemService itemService;

    /**
     * @Description: 예약 등록
     **/
    public ReservationResponseDTO addReview(ReservationRequestDTO reservationRequestDTO, Long itemId, Users user) {
        Item itemByItemId = itemService.getItemByItemId(itemId);
        validateTimeOrder(reservationRequestDTO);
        validateDuplicateReservation(itemByItemId,reservationRequestDTO.getStartDate(),reservationRequestDTO.getEndDate());

        Reservation entity = ReservationRequestDTO.toEntity(reservationRequestDTO, user, itemByItemId);
        return ReservationResponseDTO.of(reservationRepository.save(entity));
    }

    /**
     * @Description: 시간 순서 확인
     **/
    public void validateTimeOrder(ReservationRequestDTO reservationRequestDTO) {
        if (reservationRequestDTO.getStartDate().isAfter(reservationRequestDTO.getEndDate())) {
            throw new ReservationExceptionHandler.WrongTimeOrder();
        }
    }

    /**
     * @Description: 중복 예약 확인
     **/
    public void validateDuplicateReservation(Item item, LocalDateTime startDate, LocalDateTime endDate ) {

        if(reservationRepository.findFirstByItemAndStartDateLessThanEqualAndEndDateGreaterThanEqual(item,endDate,startDate).isPresent()){
            throw new ReservationExceptionHandler.DuplicateReservation();
        }

    }

    /**
     * @Description: 전체 예약 조회
     **/
    public List<ReservationResponseDTO> getReservationList() {
        return reservationRepository.findAll().stream().map(ReservationResponseDTO::of).toList();
    }

    /**
    * @Description: 해당 날짜의 예약 조회
    **/
    public List<ReservationResponseDTO> getReservationByIdAndDate(Long reservationId, LocalDateTime date) {

        LocalDateTime startDate = date.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endDate = date.withHour(23).withMinute(59).withSecond(59);

        Item itemByItemId = itemService.getItemByItemId(reservationId);
        List<ReservationResponseDTO> list = reservationRepository.findAllByItemAndStartDateLessThanEqualAndEndDateGreaterThan(itemByItemId, endDate, startDate)
                .stream().map(ReservationResponseDTO::of).toList();
        return list;
    }
}

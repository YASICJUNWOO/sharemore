package com.kjw.sharemore.reservation.service;

import com.kjw.sharemore.apiPayLoad.exception.handler.ReservationExceptionHandler;
import com.kjw.sharemore.item.normalItem.entity.Item;
import com.kjw.sharemore.item.normalItem.service.ItemService;
import com.kjw.sharemore.reservation.Reservation;
import com.kjw.sharemore.reservation.dto.request.ReservationRequestDTO;
import com.kjw.sharemore.reservation.dto.response.ReservationResponseDTO;
import com.kjw.sharemore.reservation.dto.response.ReservationUserResponseDTO;
import com.kjw.sharemore.reservation.repository.ReservationRepository;
import com.kjw.sharemore.users.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ItemService itemService;

    /**
     * @Description: 예약 등록
     **/
    public ReservationResponseDTO addReview(ReservationRequestDTO reservationRequestDTO, Long itemId, Users user) {
        Item itemByItemId = itemService.getItemByItemId(itemId);
        validateTimeOrder(reservationRequestDTO);
        validateDuplicateReservation(itemByItemId, reservationRequestDTO.getStartDate(), reservationRequestDTO.getEndDate());

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
    public void validateDuplicateReservation(Item item, LocalDateTime startDate, LocalDateTime endDate) {

        if (reservationRepository.findFirstByItemAndStartDateLessThanEqualAndEndDateGreaterThanEqual(item, endDate, startDate).isPresent()) {
            throw new ReservationExceptionHandler.DuplicateReservation();
        }

    }

    /**
     * @Description: 전체 예약 조회
     **/
    public List<ReservationResponseDTO> getReservationList() {
        return reservationRepository.findAll().stream().map(ReservationResponseDTO::of).toList();
    }

    public List<ReservationResponseDTO> getReservationList(Long itemId) {
        Item itemByItemId = itemService.getItemByItemId(itemId);
        return reservationRepository.findAllByItem(itemByItemId).stream().map(ReservationResponseDTO::of).toList();
    }

    public List<ReservationUserResponseDTO> getReservationByUser(Users user) {
        return reservationRepository.findAllByUser(user).stream().map(ReservationUserResponseDTO::of).toList();
    }

    /**
     * @Description: 해당 날짜의 예약 조회
     **/
    public List<ReservationResponseDTO> getReservationByIdAndDate(Long itemId, LocalDateTime date) {

        LocalDateTime startDate = date.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endDate = date.withHour(23).withMinute(59).withSecond(59);
        Item itemByItemId = itemService.getItemByItemId(itemId);
        List<ReservationResponseDTO> list = reservationRepository.findAllByItemAndStartDateLessThanEqualAndEndDateGreaterThan(itemByItemId, endDate, startDate)
                .stream().map(ReservationResponseDTO::of).toList();
        return list;
    }

    public List<String> getReservationByMonth(Long itemId, int year, int month) {

        List<String> reservationDays = new ArrayList<>();

        // 해당 년 월의 날짜 범위
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);

        for (int i = 1; i <= startDate.getDayOfMonth(); i++) {
            List<ReservationResponseDTO> reservationByIdAndDate = getReservationByIdAndDate(itemId, startDate);
            if (!reservationByIdAndDate.isEmpty()) {
                if( month < 10) {
                    reservationDays.add("0" + month + "-" + i);
                } else {
                    reservationDays.add(month + "-" + i);
                }
            }
            startDate = startDate.plusDays(1);
        }

        return reservationDays;
    }

    public Boolean checkReservation(ReservationRequestDTO reservationRequestDTO, Long itemId) {
        Item itemByItemId = itemService.getItemByItemId(itemId);
        validateTimeOrder(reservationRequestDTO);
        validateDuplicateReservation(itemByItemId, reservationRequestDTO.getStartDate(), reservationRequestDTO.getEndDate());
        return true;
    }
}

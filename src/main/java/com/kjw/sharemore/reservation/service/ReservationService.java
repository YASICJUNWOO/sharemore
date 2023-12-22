package com.kjw.sharemore.reservation.service;

import com.kjw.sharemore.apiPayLoad.exception.handler.ReservationExceptionHandler;
import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.item.service.ItemService;
import com.kjw.sharemore.reservation.Reservation;
import com.kjw.sharemore.reservation.converter.ReservationConverter;
import com.kjw.sharemore.reservation.dto.ReservationRequestDTO;
import com.kjw.sharemore.reservation.dto.ReservationResponseDTO;
import com.kjw.sharemore.reservation.repository.ReservationRepository;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final ItemService itemService;

    public ReservationResponseDTO addReview(ReservationRequestDTO reservationRequestDTO) {
        Users userByEmail = userService.getUserByEmail(reservationRequestDTO.getUserEmail());
        Item itemByName = itemService.getItemByName(reservationRequestDTO.getItemName());
        validateTimeOrder(reservationRequestDTO);
        validateDuplicateReservation(itemByName,reservationRequestDTO);
        Reservation entity = ReservationConverter.toEntity(reservationRequestDTO, userByEmail, itemByName);
        return ReservationConverter.toDto(reservationRepository.save(entity));
    }

    public void validateTimeOrder(ReservationRequestDTO reservationRequestDTO) {
        if (reservationRequestDTO.getStartDate().isAfter(reservationRequestDTO.getEndDate())) {
            throw new ReservationExceptionHandler.WrongTimeOrder();
        }
    }

    /**
    * @methodName : validateDuplicateReservation
    * @param :
    * @return :
    * @Description: 중복된 예약이 있는지 검사
    * @note:
    **/
    public void validateDuplicateReservation(Item item, ReservationRequestDTO reservationRequestDTO) {
        reservationRepository.findByItemAndStartDateGreaterThanEqualAndEndDateLessThanEqual(item, reservationRequestDTO.getStartDate(), reservationRequestDTO.getEndDate())
                .ifPresent(reservation -> {
                    throw new ReservationExceptionHandler.DuplicateReservation();
                });
    }

    public List<ReservationResponseDTO> getReservationList() {
        return reservationRepository.findAll().stream().map(ReservationConverter::toDto).toList();
    }

}

package com.kjw.sharemore.reservation.service;

import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.item.service.ItemService;
import com.kjw.sharemore.reivew.dto.ReviewRequestDTO;
import com.kjw.sharemore.reivew.dto.ReviewResponseDTO;
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
        log.info("itenByName: {}", itemByName.getName());
        Reservation entity = ReservationConverter.toEntity(reservationRequestDTO, userByEmail, itemByName);
        return ReservationConverter.toDto(reservationRepository.save(entity));
    }

    public List<ReservationResponseDTO> getReservationList() {
        return reservationRepository.findAll().stream().map(ReservationConverter::toDto).toList();
    }

}

package com.kjw.sharemore.reservation.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.reservation.dto.request.ReservationRequestDTO;
import com.kjw.sharemore.reservation.dto.response.ReservationResponseDTO;
import com.kjw.sharemore.reservation.service.ReservationService;
import com.kjw.sharemore.users.entity.Users;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /**
    * @Description:  전체 예약 조회
    * @param :
    * @path :
    * @body :
    * @return :
    **/
    @GetMapping
    public ApiResponse<List<ReservationResponseDTO>> getReservationList() {
        return ApiResponse.onSuccess(reservationService.getReservationList());
    }

    /**
    * @Description: 해당 날짜의 예약 조회
    * @param :
    * @path :
    * @body :
    * @return :
    **/
    @GetMapping("/{reservationId}")
    public ApiResponse<List<ReservationResponseDTO>> getReservationByIdAndDate(@PathVariable(name = "reservationId") Long reservationId,
                                                                         @RequestParam(name = "date") LocalDateTime date) {
        return ApiResponse.onSuccess(reservationService.getReservationByIdAndDate(reservationId, date));
    }
    
    /**
    * @Description: 예약 등록
    * @param :
    * @path :
    * @body :
    * @return :
    **/
    @PostMapping("/{itemId}")
    public ApiResponse<ReservationResponseDTO> postReservation(@Valid @RequestBody ReservationRequestDTO reservationRequestDTO,
                                                               @PathVariable(name = "itemId") Long itemId,
                                                               @AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(reservationService.addReview(reservationRequestDTO, itemId, user));
    }

}

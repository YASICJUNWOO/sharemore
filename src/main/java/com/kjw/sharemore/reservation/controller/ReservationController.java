package com.kjw.sharemore.reservation.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.reservation.dto.ReservationRequestDTO;
import com.kjw.sharemore.reservation.dto.ReservationResponseDTO;
import com.kjw.sharemore.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /**
    * @methodName : getReservationList
    * @param :
    * @return :
    * @Description: 전체 예약 조회
    * @note:
    **/
    @GetMapping
    public ApiResponse<List<ReservationResponseDTO>> getReservationList() {
        return ApiResponse.onSuccess(reservationService.getReservationList());
    }

    /**
    * @methodName : getReservationByIdAndDate
    * @param :date (해당 날짜)
     * @path : reservationId (예약 아이디)
    * @return :
    * @Description: 날짜와 아이템 id로 해당 날짜의 예약 조회
    * @note:
    **/
    @GetMapping("/{reservationId}")
    public ApiResponse<List<ReservationResponseDTO>> getReservationByIdAndDate(@PathVariable(name = "reservationId") Long reservationId,
                                                                         @RequestParam(name = "date") LocalDateTime date) {
        return ApiResponse.onSuccess(reservationService.getReservationByIdAndDate(reservationId, date));
    }
    
    /**
    * @methodName : postReservation
    * @param :
     * @path : itemId (아이템 아이디)
     * @body : ReservationRequestDTO
    * @return :
    * @Description: 예약 추가
    * @note:
    **/
    @PostMapping("/{itemId}")
    public ApiResponse<ReservationResponseDTO> postReservation(@Valid @RequestBody ReservationRequestDTO reservationRequestDTO,
                                                               @PathVariable(name = "itemId") Long itemId) {
        return ApiResponse.onSuccess(reservationService.addReview(reservationRequestDTO, itemId));
    }

}

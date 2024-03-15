package com.kjw.sharemore.domain.reservation.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.domain.reservation.dto.response.ReservationResponseDTO;
import com.kjw.sharemore.domain.reservation.service.ReservationQueryService;
import com.kjw.sharemore.domain.reservation.dto.request.ReservationRequestDTO;
import com.kjw.sharemore.domain.reservation.service.ReservationService;
import com.kjw.sharemore.domain.users.entity.Users;
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
    private final ReservationQueryService reservationQueryService;

    @GetMapping("/date")
    public ApiResponse<List<ReservationResponseDTO.Simple>> getReservationByIdAndDate(@RequestParam(name = "itemId") Long itemId,
                                                                                      @RequestParam(name = "date") LocalDateTime date) {
        return ApiResponse.onSuccess(reservationQueryService.getReservationByIdAndDate(itemId, date));
    }

    @GetMapping
    public ApiResponse<List<ReservationResponseDTO.Simple>> getReservationById(@PathVariable(name = "itemId") Long itemId) {
        return ApiResponse.onSuccess(reservationQueryService.getReservationList(itemId));
    }

    @PostMapping
    public ApiResponse<ReservationResponseDTO.Detail> postReservation(@Valid @RequestBody ReservationRequestDTO reservationRequestDTO,
                                                                      @AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(reservationService.adddReservation(reservationRequestDTO, user));
    }

    @GetMapping("/month")
    public ApiResponse<List<String>> getReservationByMonth(@RequestParam(name = "itemId") Long itemId,
                                                           @RequestParam(name = "year") int year,
                                                           @RequestParam(name = "month") int month) {
        return ApiResponse.onSuccess(reservationService.getReservationByMonth(itemId, year, month));
    }

    @PostMapping("/check")
    public ApiResponse<Boolean> checkReservation(@RequestBody ReservationRequestDTO reservationRequestDTO) {
        return ApiResponse.onSuccess(reservationService.checkReservation(reservationRequestDTO));
    }

}

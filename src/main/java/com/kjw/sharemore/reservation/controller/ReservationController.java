package com.kjw.sharemore.reservation.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.reservation.dto.ReservationRequestDTO;
import com.kjw.sharemore.reservation.dto.ReservationResponseDTO;
import com.kjw.sharemore.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public ApiResponse<List<ReservationResponseDTO>> getReservationList() {
        return ApiResponse.onSuccess(reservationService.getReservationList());
    }

    @PostMapping
    public ApiResponse<ReservationResponseDTO> postReservation(@Valid @RequestBody ReservationRequestDTO reservationRequestDTO) {
        return ApiResponse.onSuccess(reservationService.addReview(reservationRequestDTO));
    }

}

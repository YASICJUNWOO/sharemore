package com.kjw.sharemore.domain.reservation.service;

import com.kjw.sharemore.apiPayLoad.exception.handler.ReservationExceptionHandler;
import com.kjw.sharemore.domain.coupon.entity.UserCoupon;
import com.kjw.sharemore.domain.coupon.service.CouponQueryService;
import com.kjw.sharemore.domain.coupon.service.UserCouponQueryService;
import com.kjw.sharemore.domain.coupon.service.UserCouponService;
import com.kjw.sharemore.domain.item.normalItem.entity.Item;
import com.kjw.sharemore.domain.item.normalItem.service.ItemQueryService;
import com.kjw.sharemore.domain.reservation.Reservation;
import com.kjw.sharemore.domain.reservation.dto.request.ReservationRequestDTO;
import com.kjw.sharemore.domain.reservation.dto.response.ReservationResponseDTO;
import com.kjw.sharemore.domain.reservation.repository.ReservationRepository;
import com.kjw.sharemore.domain.users.entity.Users;
import com.kjw.sharemore.domain.users.service.UserService;
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
    private final ItemQueryService itemQueryService;
    private final ReservationQueryService reservationQueryService;
    private final CouponQueryService couponQueryService;
    private final UserCouponQueryService userCouponQueryService;
    private final UserCouponService userCouponService;
    private final UserService userService;
    private final ReservationLockService reservationLockService;

    /**
     * @Description: 예약 등록
     **/
    public ReservationResponseDTO.Detail adddReservation(ReservationRequestDTO reservationRequestDTO, Users user) {
        checkDuplicationInDB(reservationRequestDTO);
        Item itemByItemId = itemQueryService.getItemByItemId(reservationRequestDTO.getItemId());

        UserCoupon coupon = existCoupon(reservationRequestDTO, user);
        Reservation entity = ReservationRequestDTO.toEntity(reservationRequestDTO, user, itemByItemId, coupon);

        userService.updatePoint(user, entity.getFinalPrice());

        return ReservationResponseDTO.Detail.of(reservationRepository.save(entity));
    }

    public UserCoupon existCoupon(ReservationRequestDTO reservationRequestDTO, Users user) {

        if (reservationRequestDTO.getCouponId() != null) {
            UserCoupon coupon = userCouponQueryService.findByUserIdAndCouponId(user.getUserId(), reservationRequestDTO.getCouponId());
            userCouponService.useCoupon(coupon);
            return coupon;
        }

        return null;
    }


    public void validateTimeOrder(ReservationRequestDTO reservationRequestDTO) {
        if (reservationRequestDTO.getStartDate().isAfter(reservationRequestDTO.getEndDate())) {
            throw new ReservationExceptionHandler.WrongTimeOrder();
        }
    }


    public Boolean checkReservation(ReservationRequestDTO reservationRequestDTO) {
        checkDuplicationInDB(reservationRequestDTO);

        try
        {
            reservationLockService.lockReservation(reservationRequestDTO.getItemId(), reservationRequestDTO.getStartDate(), reservationRequestDTO.getEndDate());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ReservationExceptionHandler.DuplicateReservation();
        }

        return true;
    }

    private void checkDuplicationInDB(ReservationRequestDTO reservationRequestDTO) {
        validateTimeOrder(reservationRequestDTO);
        reservationQueryService.validateDuplicateReservation(reservationRequestDTO);
    }

    public List<String> getReservationByMonth(Long itemId, int year, int month) {

        List<String> reservationDays = new ArrayList<>();

        // 해당 년 월의 날짜 범위
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);

        for (int i = 1; i <= startDate.getDayOfMonth(); i++) {
            List<ReservationResponseDTO.Simple> reservationByIdAndDate =
                    reservationQueryService.getReservationByIdAndDate(itemId, startDate);
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
}

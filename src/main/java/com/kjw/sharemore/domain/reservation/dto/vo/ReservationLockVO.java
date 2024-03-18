package com.kjw.sharemore.domain.reservation.dto.vo;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@ToString
public class ReservationLockVO {

    private Long itemId;
    private Long startDate;
    private Long endDate;

    public static ReservationLockVO of(Long itemId, Long startDate, Long endDate) {
        return ReservationLockVO.builder()
                .itemId(itemId)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

}

package com.kjw.sharemore.domain.reservation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjw.sharemore.domain.reservation.dto.vo.ReservationLockVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationLockService {

    private final RedisTemplate<String,String> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String lockReservation(Long itemId, LocalDateTime startDate, LocalDateTime endDate) throws JsonProcessingException, IllegalArgumentException{
        Long startDateValue = toEpochMilli(startDate);
        Long endDateValue = toEpochMilli(endDate);
        String reservationLockListKey = "reservation:" + itemId;

        log.info("lockReservation: itemId: {}, startDate: {}, endDate: {}", itemId, startDate, endDate);
        validReservation(reservationLockListKey, endDateValue, startDateValue);

        ReservationLockVO reservationLockVO = ReservationLockVO.of(itemId, startDateValue, endDateValue);

        String reservationKey = saveReservationValue(reservationLockVO);
        saveLock(reservationLockListKey, reservationKey);

        return reservationKey;
    }

    private void validReservation(String reservationLockListKey, Long endDateValue, Long startDateValue) throws IllegalArgumentException {
        List<String> strings = Objects.requireNonNull(redisTemplate.opsForList().range(reservationLockListKey, 0, -1));

        log.info(String.valueOf(strings.size()));

        strings.forEach(reservationKey -> {
            String reservationValue = redisTemplate.opsForValue().get(reservationKey);
            ReservationLockVO reservationLockValue = null;

            if(reservationValue == null) {
                return;
            }

            try {
                reservationLockValue = objectMapper.readValue(reservationValue, ReservationLockVO.class);
                log.info("reservationLockValue: {}", reservationLockValue.toString());
                log.info(String.valueOf(reservationLockValue.getItemId()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (reservationLockValue.getStartDate() <= endDateValue && reservationLockValue.getEndDate() >= startDateValue) {


                LocalDateTime start = Instant.ofEpochMilli(endDateValue).atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime end = Instant.ofEpochMilli(startDateValue).atZone(ZoneId.systemDefault()).toLocalDateTime();

                log.info("start: {}, end: {}", start, end);

                LocalDateTime start1 = Instant.ofEpochMilli(reservationLockValue.getEndDate()).atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime end1 = Instant.ofEpochMilli(reservationLockValue.getStartDate()).atZone(ZoneId.systemDefault()).toLocalDateTime();

                log.info("start1: {}, end1: {}", start1, end1);
                throw new IllegalArgumentException("이미 예약된 시간입니다.");
            }

        });
    }

    // 예약 정보 저장된 키 리스트에 저장
    private void saveLock(String reservationLockListKey, String reservationKey) {
        redisTemplate.opsForList().leftPush(reservationLockListKey, reservationKey);
    }

    // 예약 정보 임시 저장
    private String saveReservationValue(ReservationLockVO reservationLockVO) throws JsonProcessingException {
        String reservationKey  = UUID.randomUUID().toString();
        String value = objectMapper.writeValueAsString(reservationLockVO);
        redisTemplate.opsForValue().set(reservationKey, value, 10, TimeUnit.MINUTES);
        return reservationKey;
    }

    public Long toEpochMilli(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

}


package com.kjw.sharemore.reservation.service;

import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.item.repositoty.ItemRepository;
import com.kjw.sharemore.reservation.Reservation;
import com.kjw.sharemore.reservation.repository.ReservationRepository;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ReservationServiceTest {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ReservationService reservationService;

    private Reservation reservation;
    private Users user;
    private Item item;

    @BeforeEach
    public void setup() {
        user = Users.builder()
                .email("test@email.com")
                .name("test")
                .password("test")
                .address("test")
                .build();
        userRepository.save(user);

        item = Item.builder()
                .name("testItem")
                .price(1000)
                .user(user)
                .category("test")
                .description("test")
                .itemImage("test")
                .build();
        itemRepository.save(item);

        reservation = Reservation.builder()
                .user(user)
                .item(item)
                .startDate(LocalDateTime.parse("2024-01-05T12:00:00"))
                .endDate(LocalDateTime.parse("2024-01-06T12:00:00"))
                .build();
        reservationRepository.save(reservation);

    }

    @Test
    @DisplayName("이후 시간에 예약할 때(성공해야함)")
    void afterReservation() {

        //given
        Reservation BeforeReservation = Reservation.builder()
                .user(user)
                .item(item)
                .startDate(LocalDateTime.parse("2024-01-06T12:00:00"))
                .endDate(LocalDateTime.parse("2024-01-07T12:00:00"))
                .build();

        LocalDateTime startDate = BeforeReservation.getStartDate();
        LocalDateTime endDate = BeforeReservation.getEndDate();

        //when
        Optional<Reservation> resultList = reservationRepository.findByItemAndStartDateLessThanEqualAndEndDateGreaterThan(item, startDate, startDate);
        Optional<Reservation> resultList2 = reservationRepository.findByItemAndStartDateGreaterThanAndStartDateLessThan(item, startDate, endDate);

        //then
        assertThat(resultList.isPresent()).isFalse();
        assertThat(resultList2.isPresent()).isFalse();
    }

    @Test
    @DisplayName("시작 시간에 걸쳐 예약할 떄")
    void BetweenStartTimeReservation() {

        //given
        Reservation BeforeReservation = Reservation.builder()
                .user(user)
                .item(item)
                .startDate(LocalDateTime.parse("2024-01-05T12:01:00"))
                .endDate(LocalDateTime.parse("2024-01-06T12:00:00"))
                .build();

        LocalDateTime startDate = BeforeReservation.getStartDate();
        LocalDateTime endDate = BeforeReservation.getEndDate();

        //when
        Optional<Reservation> resultList = reservationRepository.findByItemAndStartDateLessThanEqualAndEndDateGreaterThan(item, startDate, startDate);
        Optional<Reservation> resultList2 = reservationRepository.findByItemAndStartDateGreaterThanAndStartDateLessThan(item, startDate, endDate);

        //then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(resultList.isPresent()).isTrue();
        softly.assertThat(resultList2.isPresent()).isFalse();
        softly.assertAll();

    }

    @Test
    @DisplayName("예약할 시간 안쪽에 있는지")
    void BetweenAllTimeReservation() {

        //given
        Reservation BeforeReservation = Reservation.builder()
                .user(user)
                .item(item)
                .startDate(LocalDateTime.parse("2024-01-04T12:00:00"))
                .endDate(LocalDateTime.parse("2024-01-06T12:00:00"))
                .build();

        LocalDateTime startDate = BeforeReservation.getStartDate();
        LocalDateTime endDate = BeforeReservation.getEndDate();

        //when
        Optional<Reservation> resultList = reservationRepository.findByItemAndStartDateLessThanEqualAndEndDateGreaterThan(item, startDate, startDate);
        Optional<Reservation> resultList2 = reservationRepository.findByItemAndStartDateGreaterThanAndStartDateLessThan(item, startDate, endDate);

        //then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(resultList.isPresent()).isFalse();
        softly.assertThat(resultList2.isPresent()).isTrue();
        softly.assertAll();

    }

    @Test
    @DisplayName("이전 시간에 예약할 때")
    void beforeReservation() {

        //given
        Reservation BeforeReservation = Reservation.builder()
                .user(user)
                .item(item)
                .startDate(LocalDateTime.parse("2024-01-04T12:00:00"))
                .endDate(LocalDateTime.parse("2024-01-05T12:00:00"))
                .build();

        LocalDateTime startDate = BeforeReservation.getStartDate();
        LocalDateTime endDate = BeforeReservation.getEndDate();

        //when
        Optional<Reservation> resultList = reservationRepository.findByItemAndStartDateLessThanEqualAndEndDateGreaterThan(item, startDate, startDate);
        Optional<Reservation> resultList2 = reservationRepository.findByItemAndStartDateGreaterThanAndStartDateLessThan(item, startDate, endDate);

        //then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(resultList.isPresent()).isFalse();
        softly.assertThat(resultList2.isPresent()).isFalse();
        softly.assertAll();

    }
}
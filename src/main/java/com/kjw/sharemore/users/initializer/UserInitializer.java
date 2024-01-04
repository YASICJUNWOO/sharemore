package com.kjw.sharemore.users.initializer;

import com.kjw.sharemore.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInitializer implements ApplicationRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    //INSERT INTO USERS (name, email, password, address)
    //VALUES ('김철수', 'chulsoo1@example.com', 'password123', '서울특별시 강남구'),
    //       ('이영희', 'younghee2@example.com', 'password456', '서울특별시 서초구'),
    //       ('박지성', 'jisung3@example.com', 'password789', '서울특별시 송파구'),
    //       ('최유나', 'yuna4@example.com', 'password321', '서울특별시 강동구'),
    //       ('김태현', 'taehyun5@example.com', 'password654', '서울특별시 관악구'),
    //       ('이민준', 'minjun6@example.com', 'password987', '서울특별시 양천구'),
    //       ('박서아', 'seoah7@example.com', 'password741', '서울특별시 동작구'),
    //       ('최준영', 'junyoung8@example.com', 'password852', '서울특별시 성동구'),
    //       ('김하은', 'haeun9@example.com', 'password963', '서울특별시 중랑구'),
    //       ('이지아', 'jia10@example.com', 'password159', '서울특별시 강북구'),
    //       ('공준우', 'joonoo3@inha.edu', '1234', '성남시 분당구');


    @Override
    public void run(ApplicationArguments args) throws Exception {

        /*System.out.println(passwordEncoder.encode("password123"));
        Users user1 = Users.builder()
                .name("김철수")
                .email("chulsoo1@example.com")
                .password(passwordEncoder.encode("password123"))
                .address("서울특별시 강남구")
                .build();

        Users user2 = Users.builder()
                .name("이영희")
                .email("younghee2@example.com")
                .password(passwordEncoder.encode("password456"))
                .address("서울특별시 서초구")
                .build();

        Users user3 = Users.builder()
                .name("박지성")
                .email("jisung3@example.com")
                .password(passwordEncoder.encode("password789"))
                .address("서울특별시 송파구")
                .build();

        Users user4 = Users.builder()
                .name("최유나")
                .email("yuna4@example.com")
                .password(passwordEncoder.encode("password321"))
                .address("서울특별시 강동구")
                .build();

        Users user5 = Users.builder()
                .name("김태현")
                .email("taehyun5@example.com")
                .password(passwordEncoder.encode("password654"))
                .address("서울특별시 관악구")
                .build();

        Users user6 = Users.builder()
                .name("이민준")
                .email("minjun6@example.com")
                .password(passwordEncoder.encode("password987"))
                .address("서울특별시 양천구")
                .build();

        Users user7 = Users.builder()
                .name("박서아")
                .email("seoah7@example.com")
                .password(passwordEncoder.encode("password741"))
                .address("서울특별시 동작구")
                .build();

        Users user8 = Users.builder()
                .name("최준영")
                .email("junyoung8@example.com")
                .password(passwordEncoder.encode("password852"))
                .address("서울특별시 성동구")
                .build();

        Users user9 = Users.builder()
                .name("김하은")
                .email("haeun9@example.com")
                .password(passwordEncoder.encode("password963"))
                .address("서울특별시 중랑구")
                .build();

        Users user10 = Users.builder()
                .name("이지아")
                .email("jia10@example.com")
                .password(passwordEncoder.encode("password159"))
                .address("서울특별시 강북구")
                .build();

        Users user11 = Users.builder()
                .name("공준우")
                .email("joonoo3@inha.edu")
                .password(passwordEncoder.encode("1234"))
                .address("성남시 분당구")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);
        userRepository.save(user9);
        userRepository.save(user10);
        userRepository.save(user11);*/

    }
}

package com.kjw.sharemore.users.service;

import com.kjw.sharemore.apiPayLoad.exception.handler.UserExceptionHandler;
import com.kjw.sharemore.reivew.entity.Review;
import com.kjw.sharemore.reivew.service.ReviewService;
import com.kjw.sharemore.users.converter.UserConverter;
import com.kjw.sharemore.users.dto.UserDetailResponseDTO;
import com.kjw.sharemore.users.dto.UserRequestDTO;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ReviewService reviewService;

    /**
     * @param :
     * @return :
     * @methodName : getUserList
     * @Description: 전체 유저 조회
     * @note:
     **/
    public List<UserDetailResponseDTO> getUserList() {
        return userRepository.findAll().stream().map(
                user -> UserDetailResponseDTO.of(user, reviewService.getReviewByReviewee(user))
        ).toList();
    }

    /**
     * @param :
     * @return :
     * @methodName : addUser
     * @Description: 회원 추가
     * @note:
     **/
    public UserDetailResponseDTO addUser(UserRequestDTO userRequestDTO) {
        isExistUser(userRequestDTO.getEmail());
        Users users = UserConverter.toEntity(userRequestDTO);
        return getUserDetail(userRepository.save(users));
    }

    private void isExistUser(String email) {
        userRepository.findByEmail(email).ifPresent(
                user -> {
                    throw new UserExceptionHandler.ExistUser();
                }
        );
    }

    public UserDetailResponseDTO getUserDetail(Users user) {
        List<Review> reviewByReviewee = reviewService.getReviewByReviewee(user);

        return UserConverter.toUserDetailResponseDTO(user,reviewByReviewee);
    }

    /**
     * @param :
     * @return :
     * @methodName : getUserByEmail
     * @Description: email로 유저 조회
     * @note:
     **/
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserExceptionHandler.NoExistUser::new);
    }

    /**
     * @param :
     * @return :
     * @methodName : updateUser
     * @Description: 유저 정보 수정
     * @note:
     **/
    public UserDetailResponseDTO updateUser(Users user, UserRequestDTO userRequestDTO) {
        user.update(userRequestDTO);
        return getUserDetail(user);
    }

    /**
     * @param :
     * @return :
     * @methodName : deleteUser
     * @Description: 유저 삭제
     * @note:
     **/
    public UserDetailResponseDTO deleteUser(String email) {
        Users user = userRepository.findByEmail(email).orElseThrow();
        userRepository.delete(user);
        return getUserDetail(user);
    }
}

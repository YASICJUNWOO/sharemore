package com.kjw.sharemore.users.service;

import com.kjw.sharemore.apiPayLoad.exception.handler.UserExceptionHandler;
import com.kjw.sharemore.item.normalItem.dto.response.ItemResponseBaseDTO;
import com.kjw.sharemore.item.normalItem.service.ItemService;
import com.kjw.sharemore.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.reivew.dto.ReviewUserPostResponseDTO;
import com.kjw.sharemore.reivew.service.ReviewService;
import com.kjw.sharemore.reservation.dto.response.ReservationUserResponseDTO;
import com.kjw.sharemore.reservation.service.ReservationService;
import com.kjw.sharemore.users.dto.UserDetailResponseDTO;
import com.kjw.sharemore.users.dto.UserRequestDTO;
import com.kjw.sharemore.users.dto.UserSimpleDetailDTO;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.repository.UserRepository;
import jakarta.transaction.Transactional;
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
    private final ItemService itemService;
    private final ReservationService reservationService;

    /**
     * @param :
     * @return :
     * @Description: 유저 리스트 조회
     * @path :
     * @body :
     **/
    public List<UserDetailResponseDTO> getUserList() {
        return userRepository.findAll().stream().map(this::getUserDetail).toList();
    }

    /**
     * @param :
     * @return :
     * @Description: 유저 상세 조회
     * @path :
     * @body :
     **/
    @Transactional
    public UserDetailResponseDTO getUserDetail(Users user) {
        List<ItemResponseBaseDTO> itemList = itemService.getItemByOwner(user);
        List<ReviewResponseDTO> reviewByReviewee = reviewService.getReviewByReviewee(user);
        List<ReviewUserPostResponseDTO> postReviewList = reviewService.getReviewByReviewer(user);
        List<ReservationUserResponseDTO> reservationList = reservationService.getReservationByUser(user);
        return UserDetailResponseDTO.of(user, itemList, reviewByReviewee, postReviewList, reservationList);
    }

    /**
     * @param :
     * @return :
     * @Description: 이메일로 유저 상세 조회
     * @path :
     * @body :
     **/
    @Transactional
    public UserSimpleDetailDTO getUserDetail(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(UserExceptionHandler.NoExistUser::new);
        List<ItemResponseBaseDTO> itemList = itemService.getItemByOwner(user);
        List<ReviewResponseDTO> reviewByReviewee = reviewService.getReviewByReviewee(user);
        return UserSimpleDetailDTO.of(user, itemList, reviewByReviewee);
    }


    /**
     * @param :
     * @return :
     * @Description: 유저 추가
     * @path :
     * @body :
     **/
    public UserDetailResponseDTO addUser(UserRequestDTO userRequestDTO) {
        isExistUser(userRequestDTO.getEmail());
        Users users = UserRequestDTO.toEntity(userRequestDTO);
        return getUserDetail(userRepository.save(users));
    }

    /**
     * @param :
     * @return :
     * @Description: 유저 중복 확인
     * @path :
     * @body :
     **/
    private void isExistUser(String email) {
        userRepository.findByEmail(email).ifPresent(
                user -> {
                    throw new UserExceptionHandler.ExistUser();
                }
        );
    }

    /**
     * @param :
     * @return :
     * @Description: 이메일로 유저 조회
     * @path :
     * @body :
     **/
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserExceptionHandler.NoExistUser::new);
    }

    /**
     * @param :
     * @return :
     * @Description: 유저 정보 수정
     * @path :
     * @body :
     **/
    public UserDetailResponseDTO updateUser(Users user, UserRequestDTO userRequestDTO) {
        user.update(userRequestDTO);
        return getUserDetail(user);
    }

    /**
     * @param :
     * @return :
     * @Description: 유저 삭제
     * @path :
     * @body :
     **/
    public UserDetailResponseDTO deleteUser(Users user) {
        userRepository.delete(user);
        return getUserDetail(user);
    }
}

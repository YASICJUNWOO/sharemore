package com.kjw.sharemore.users.service;

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

    /**
     * @param :
     * @return :
     * @methodName : getUserList
     * @Description: 전체 유저 조회
     * @note:
     **/
    public List<UserDetailResponseDTO> getUserList() {
        return userRepository.findAll().stream().map(
                UserConverter::toUserDetailResponseDTO
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
        Users users = UserConverter.toEntity(userRequestDTO);
        return UserConverter.toUserDetailResponseDTO(userRepository.save(users));
    }

    /**
     * @param :
     * @return :
     * @methodName : getUserByEmail
     * @Description: email로 유저 조회
     * @note:
     **/
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    /**
     * @param :
     * @return :
     * @methodName : updateUser
     * @Description: 유저 정보 수정
     * @note:
     **/
    public UserDetailResponseDTO updateUser(String email, UserRequestDTO userRequestDTO) {
        Users user = userRepository.findByEmail(email).orElseThrow();
        user.update(userRequestDTO);
        return UserConverter.toUserDetailResponseDTO(userRepository.save(user));
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
        return UserConverter.toUserDetailResponseDTO(user);
    }
}

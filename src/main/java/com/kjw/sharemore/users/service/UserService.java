package com.kjw.sharemore.users.service;

import com.kjw.sharemore.apiPayLoad.exception.handler.UserExceptionHandler;
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

    public List<UserDetailResponseDTO> getUserList() {
        return userRepository.findAll().stream().map(
                UserConverter::toUserDetailResponseDTO
        ).toList();
    }

    public UserDetailResponseDTO addUser(UserRequestDTO userRequestDTO) {
        isExistUser(userRequestDTO.getEmail());
        Users users = UserConverter.toEntity(userRequestDTO);
        return UserConverter.toUserDetailResponseDTO(userRepository.save(users));
    }

    private void isExistUser(String email) {
        userRepository.findByEmail(email).ifPresent(
                user -> {
                    throw new UserExceptionHandler.ExistUser();
                }
        );
    }

    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserExceptionHandler.NoExistUser::new);
    }

    public UserDetailResponseDTO updateUser(String email, UserRequestDTO userRequestDTO) {
        Users user = userRepository.findByEmail(email).orElseThrow();
        user.update(userRequestDTO);
        return UserConverter.toUserDetailResponseDTO(userRepository.save(user));
    }

    public UserDetailResponseDTO deleteUser(String email) {
        Users user = userRepository.findByEmail(email).orElseThrow();
        userRepository.delete(user);
        return UserConverter.toUserDetailResponseDTO(user);
    }
}

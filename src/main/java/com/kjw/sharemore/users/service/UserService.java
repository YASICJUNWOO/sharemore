package com.kjw.sharemore.users.service;

import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.converter.UserConverter;
import com.kjw.sharemore.users.dto.UserRequestDTO;
import com.kjw.sharemore.users.dto.UserResponseDTO;
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

    public List<UserResponseDTO> getUserList() {
        return userRepository.findAll().stream().map(
                UserConverter::toUserDetailResponseDTO
        ).toList();
    }

    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        Users users = UserConverter.toEntity(userRequestDTO);
        return UserConverter.toUserDetailResponseDTO(userRepository.save(users));
    }

    public Users getUserByEmail(String email) {
        log.info("email: {}", email);
        return userRepository.findByEmail(email).orElseThrow();
    }

}

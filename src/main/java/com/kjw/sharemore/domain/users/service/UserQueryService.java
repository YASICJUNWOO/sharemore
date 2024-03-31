package com.kjw.sharemore.domain.users.service;

import com.kjw.sharemore.apiPayLoad.exception.handler.UserExceptionHandler;
import com.kjw.sharemore.domain.users.entity.Users;
import com.kjw.sharemore.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserRepository userRepository;

    public Users getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserExceptionHandler.NoExistUser::new);
    }

    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserExceptionHandler.NoExistUser::new);
    }

    public void isExistUser(String email) {
        userRepository.findByEmail(email).orElseThrow(UserExceptionHandler.ExistUser::new);
    }

}

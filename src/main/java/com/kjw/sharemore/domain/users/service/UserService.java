package com.kjw.sharemore.domain.users.service;

import com.kjw.sharemore.domain.users.repository.UserRepository;
import com.kjw.sharemore.domain.users.dto.UserRequestDTO;
import com.kjw.sharemore.domain.users.dto.UserResponseDTO;
import com.kjw.sharemore.domain.users.entity.Users;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserRedisService userRedisService;
    private final UserQueryService userQueryService;

    @Transactional
    public Object getUserDetail(Long userId) {

        if(isExistUser() == null){
            return UserResponseDTO.Detail.of(userQueryService.getUserById(userId), 0L);
        }

        Users requestUser = isExistUser();
        Users targetUser = userQueryService.getUserById(userId);
        Long viewCount = userRedisService.addViewCount(targetUser.getUserId().toString(), requestUser.getUserId().toString());

        return UserResponseDTO.Detail.of(targetUser, viewCount);
    }

    public Users isExistUser(){
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return null;
        }
        return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public UserResponseDTO.Simple addUser(UserRequestDTO userRequestDTO) {
        userQueryService.isExistUser(userRequestDTO.getEmail());
        Users users = UserRequestDTO.toEntity(userRequestDTO);
        return UserResponseDTO.Simple.of(userRepository.save(users));
    }

    public UserResponseDTO.Simple updateUser(@AuthenticationPrincipal Users user,
                                             UserRequestDTO userRequestDTO) {
        user.update(userRequestDTO);
        return UserResponseDTO.Simple.of(user);
    }

    public UserResponseDTO.Simple deleteUser(Users user) {
        userRepository.delete(user);
        return UserResponseDTO.Simple.of(user);
    }

}

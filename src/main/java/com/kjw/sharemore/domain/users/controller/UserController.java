package com.kjw.sharemore.domain.users.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.domain.users.dto.UserRequestDTO;
import com.kjw.sharemore.domain.users.dto.UserResponseDTO;
import com.kjw.sharemore.domain.users.entity.Users;
import com.kjw.sharemore.domain.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}/detail")
    public ApiResponse<?> getUserDetail(@PathVariable(name = "userId") Long userId) {
        return ApiResponse.onSuccess(userService.getUserDetail(userId));
    }

    @PostMapping
    public ApiResponse<UserResponseDTO.Simple> postUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return ApiResponse.onSuccess(userService.addUser(userRequestDTO));
    }

    @PatchMapping
    public ApiResponse<UserResponseDTO.Simple> patchUser(@AuthenticationPrincipal Users user,
                                                        @RequestBody UserRequestDTO userRequestDTO) {
        return ApiResponse.onSuccess(userService.updateUser(user, userRequestDTO));
    }

    @DeleteMapping
    public ApiResponse<UserResponseDTO.Simple> deleteUser(@AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(userService.deleteUser(user));
    }

}

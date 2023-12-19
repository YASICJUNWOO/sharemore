package com.kjw.sharemore.users.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.users.converter.UserConverter;
import com.kjw.sharemore.users.dto.UserDetailResponseDTO;
import com.kjw.sharemore.users.dto.UserRequestDTO;
import com.kjw.sharemore.users.dto.UserResponseDTO;
import com.kjw.sharemore.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //전체 유저 조회
    @GetMapping
    public ApiResponse<List<UserDetailResponseDTO>> getUserList() {
        return ApiResponse.onSuccess(userService.getUserList());
    }

    //유저 개별 조회
    @GetMapping("/{email}")
    public ApiResponse<UserDetailResponseDTO> getUser(@PathVariable String email) {
        return ApiResponse.onSuccess(UserConverter.toUserDetailResponseDTO(userService.getUserByEmail(email)));
    }

    //유저 등록
    @PostMapping
    public ApiResponse<UserDetailResponseDTO> postUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ApiResponse.onSuccess(userService.addUser(userRequestDTO));
    }

    //유저 수정
    @PatchMapping("/{email}")
    public ApiResponse<UserDetailResponseDTO> patchUser(@PathVariable String email, @RequestBody UserRequestDTO userRequestDTO) {
        return ApiResponse.onSuccess(userService.updateUser(email, userRequestDTO));
    }

    //유저 삭제
    @DeleteMapping("/{email}")
    public ApiResponse<String> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ApiResponse.onSuccess("success");
    }

}

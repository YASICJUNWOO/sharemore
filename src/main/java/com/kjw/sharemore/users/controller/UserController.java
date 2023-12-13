package com.kjw.sharemore.users.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
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
    public ApiResponse<List<UserResponseDTO>> getUserList() {
        return ApiResponse.onSuccess(userService.getUserList());
    }

    //유저 등록
    @PostMapping
    public ApiResponse<UserResponseDTO> postUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ApiResponse.onSuccess(userService.addUser(userRequestDTO));
    }

}

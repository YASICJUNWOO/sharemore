package com.kjw.sharemore.users.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.users.dto.UserDetailResponseDTO;
import com.kjw.sharemore.users.dto.UserRequestDTO;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
    * @methodName : getUserList
    * @param :
    * @return :
    * @Description: 전체 유저 조회
    * @note:
    **/
    @GetMapping
    public ApiResponse<List<UserDetailResponseDTO>> getUserList() {
        return ApiResponse.onSuccess(userService.getUserList());
    }


    /**
     * @methodName : getUser
     * @param :
     * @path : emeail
     * @return :
     * @Description: 유저 상세 조회
     * @note: defaultUser로 일단 조회
     **/
    @GetMapping("/detail")
    public ApiResponse<UserDetailResponseDTO> getUserDetail(@AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(userService.getUserDetail(user));
    }

    //유저 등록
    @PostMapping
    public ApiResponse<UserDetailResponseDTO> postUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return ApiResponse.onSuccess(userService.addUser(userRequestDTO));
    }

    //유저 수정
    @PatchMapping()
    public ApiResponse<UserDetailResponseDTO> patchUser(@AuthenticationPrincipal Users user,
                                                        @RequestBody UserRequestDTO userRequestDTO) {
        return ApiResponse.onSuccess(userService.updateUser(user, userRequestDTO));
    }

    //유저 삭제
    @DeleteMapping("/{email}")
    public ApiResponse<String> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ApiResponse.onSuccess("success");
    }

}

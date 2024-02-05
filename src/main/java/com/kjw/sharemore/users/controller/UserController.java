package com.kjw.sharemore.users.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.users.converter.UserConverter;
import com.kjw.sharemore.users.dto.UserDetailResponseDTO;
import com.kjw.sharemore.users.dto.UserRequestDTO;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //기본 유저 생성
    //       ('공준우', 'joonoo3@inha.edu', '1234', '성남시 분당구');
    private final Users defaultUser = Users.builder()
            .name("공준우")
            .email("joonoo3@inha.edu")
            .password("1234")
            .address("성남시 분당구")
            .build();


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
    * @methodName : getMyDetail
    * @param :
     * @path : emeail
    * @return :
    * @Description: 유저 상세 조회
    * @note: defaultUser로 일단 조회
    **/
    @GetMapping("/detail")
    public ApiResponse<UserDetailResponseDTO> getMyDetail() {
        return ApiResponse.onSuccess(userService.getUserDetailByEmail(defaultUser.getEmail()));
    }

    /**
     * @methodName : getUser
     * @param :
     * @path : emeail
     * @return :
     * @Description: 유저 상세 조회
     * @note: defaultUser로 일단 조회
     **/
    @GetMapping("/detail/{email}")
    public ApiResponse<UserDetailResponseDTO> getUserDetail(@PathVariable("email") String email) {
        return ApiResponse.onSuccess(userService.getUserDetailByEmail(email));
    }

    //유저 등록
    @PostMapping
    public ApiResponse<UserDetailResponseDTO> postUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
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

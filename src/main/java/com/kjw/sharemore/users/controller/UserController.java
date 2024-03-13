package com.kjw.sharemore.users.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.users.dto.UserDetailResponseDTO;
import com.kjw.sharemore.users.dto.UserRequestDTO;
import com.kjw.sharemore.users.dto.UserSimpleDetailDTO;
import com.kjw.sharemore.users.dto.UserSimpleResponseDTO;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.service.UserRedisService;
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
    * @Description: 유저 상세 조회
    * @param :
    * @path :
    * @body :
    * @return :
    **/
    @GetMapping("/{userId}/detail")
    public ApiResponse<?> getUserDetail(@PathVariable(name = "userId") Long userId,
                                                            @AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(userService.getUserDetail(userId,user));
    }

    /**
    * @Description: 유저 등록 ( 추후 Auth로 변경 예정 )
    * @param :
    * @path :
    * @body : UserRequestDTO
    * @return :
    **/
    @PostMapping
    public ApiResponse<UserSimpleResponseDTO> postUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return ApiResponse.onSuccess(userService.addUser(userRequestDTO));
    }

    /**
    * @Description: 유저 수정
    * @param :
    * @path :
    * @body : UserRequestDTO
    * @return :
    **/
    @PatchMapping()
    public ApiResponse<UserSimpleResponseDTO> patchUser(@AuthenticationPrincipal Users user,
                                                        @RequestBody UserRequestDTO userRequestDTO) {
        return ApiResponse.onSuccess(userService.updateUser(user, userRequestDTO));
    }

    /**
    * @Description: 유저 삭제
    * @param :
    * @path :
    * @body :
    * @return :
    **/
    @DeleteMapping()
    public ApiResponse<UserSimpleResponseDTO> deleteUser(@AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(userService.deleteUser(user));
    }

}

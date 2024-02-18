package com.kjw.sharemore.global.security.jwt;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ApiResponse<TokenInfo> login(@RequestBody LoginDto dto) {
        TokenInfo token = authService.login(dto);
        return ApiResponse.onSuccess(token);
    }
}

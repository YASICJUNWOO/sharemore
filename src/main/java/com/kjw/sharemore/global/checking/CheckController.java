package com.kjw.sharemore.global.checking;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class CheckController {

    @GetMapping("/ok")
    public ApiResponse<String> check() {
        return ApiResponse.onSuccess("ok");
    }

    @PostMapping("/fail")
    public ApiResponse<String> loginFail() {
        return ApiResponse.onFailure("401", "login fail", "fail");
    }

}

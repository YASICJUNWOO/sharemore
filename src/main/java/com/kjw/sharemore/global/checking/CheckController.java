package com.kjw.sharemore.global.checking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class CheckController {

    @GetMapping
    public String check() {
        return "ok";
    }

}

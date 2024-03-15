package com.kjw.sharemore.pay;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pay")
public class PayController {

    private final PaymentService paymentService;

    @PostMapping("/calculate")
    public ApiResponse<PaymentResponseDTO> calculate(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        return ApiResponse.onSuccess(paymentService.calculate(paymentRequestDTO));
    }

}

package ru.t1.pmorozov.controller;

import org.springframework.web.bind.annotation.*;
import ru.t1.pmorozov.dto.PaymentReqDto;
import ru.t1.pmorozov.dto.PaymentRespDto;
import ru.t1.pmorozov.service.PaymentService;

import java.util.HashSet;


@RestController
@RequestMapping(path = "/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("products/getByUserId/{userId}")
    public HashSet productsByUserId(@PathVariable Long userId) {
        return paymentService.getProductsByUserId(userId);
    }

    @PostMapping("/pay")
    public PaymentRespDto doPayment(@RequestBody PaymentReqDto payReqDto) {
        return paymentService.doPayment(payReqDto);
    }
}

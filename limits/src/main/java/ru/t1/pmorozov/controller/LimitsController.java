package ru.t1.pmorozov.controller;

import org.springframework.web.bind.annotation.*;
import ru.t1.pmorozov.dto.LimitReqDto;
import ru.t1.pmorozov.dto.LimitRespDto;
import ru.t1.pmorozov.service.LimitService;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/api/v1/limits")
public class LimitsController {

    private final LimitService limitService;

    public LimitsController(LimitService limitService) {
        this.limitService = limitService;
    }

    @GetMapping("/getByUserId/{userId}")
    public BigDecimal getLimitValueByUserId(@PathVariable Long userId) {
        return limitService.getLimitByUserId(userId).getCurrentValue();
    }

    @PostMapping("/max/increase")
    public LimitRespDto increaseLimitMaxValue(@RequestBody LimitReqDto limitReqDto) {
        return limitService.increaseMaxUserLimit(limitReqDto);
    }

    @PostMapping("/max/decrease")
    public LimitRespDto decreaseLimitMaxValue(@RequestBody LimitReqDto limitReqDto) {
        return limitService.decreaseMaxUserLimit(limitReqDto);
    }

    @PostMapping("/current/increase")
    public LimitRespDto increaseLimitCurrentValue(@RequestBody LimitReqDto limitReqDto) {
        return limitService.increaseCurrentLimit(limitReqDto);
    }

    @PostMapping("/current/decrease")
    public LimitRespDto decreaseLimitCurrentValue(@RequestBody LimitReqDto limitReqDto) {
        return limitService.decreaseCurrentLimit(limitReqDto);
    }

}

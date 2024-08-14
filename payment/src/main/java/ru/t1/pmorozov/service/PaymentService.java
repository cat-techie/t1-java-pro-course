package ru.t1.pmorozov.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.t1.pmorozov.config.ProductsRestTemplateProperties;
import ru.t1.pmorozov.dto.PaymentReqDto;
import ru.t1.pmorozov.dto.PaymentRespDto;

import java.util.HashSet;

@Service
public class PaymentService {
    private final String productsUrl;
    private final RestTemplate restTemplate;

    public PaymentService(RestTemplate restTemplate, ProductsRestTemplateProperties properties) {
        productsUrl = properties.getUrl();
        this.restTemplate = restTemplate;
    }

    public HashSet getProductsByUserId(Long userId) {
        ResponseEntity<HashSet> entity = restTemplate.getForEntity(productsUrl + "/getByUserId/{userId}", HashSet.class, userId);
        return entity.getBody();
    }

    public PaymentRespDto doPayment(PaymentReqDto payReqDto) {
        String url = productsUrl + "/payment";
        ResponseEntity<PaymentRespDto> entity = restTemplate.postForEntity(url, payReqDto, PaymentRespDto.class);
        return entity.getBody();
    }
}

package ru.t1.pmorozov.controller;

import org.springframework.web.bind.annotation.*;
import ru.t1.pmorozov.dto.PaymentReqDto;
import ru.t1.pmorozov.dto.PaymentRespDto;
import ru.t1.pmorozov.dto.ProductDto;
import ru.t1.pmorozov.entity.Product;
import ru.t1.pmorozov.service.ProductService;
import ru.t1.pmorozov.service.UserProductService;

import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final UserProductService userProductService;

    public ProductController(ProductService productService, UserProductService userProductService) {
        this.productService = productService;
        this.userProductService = userProductService;
    }

    @GetMapping("/getByUserId/{userId}")
    public Set<ProductDto> productsByUserId(@PathVariable Long userId) {
        return userProductService.getProductsByUserId(userId);
    }

    @GetMapping("/getByProductId/{productId}")
    public ProductDto productById(@PathVariable Long productId) {
        Product product = productService.get(productId).orElseThrow();
        return new ProductDto(
                product.getId(),
                product.getAccNumber(),
                product.getBalance(),
                product.getProdType()
        );
    }

    @PostMapping("/payment")
    public PaymentRespDto doPayment(@RequestBody PaymentReqDto paymentReqDto) {
        return userProductService.doPayment(paymentReqDto);
    }
}

package ru.t1.pmorozov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.t1.pmorozov.data.Product;
import ru.t1.pmorozov.service.ProductService;
import ru.t1.pmorozov.service.UserProductService;
import java.util.Optional;
import java.util.Set;

@RestController("/")
public class ProductController {
    private final ProductService productService;
    private final UserProductService userProductService;

    public ProductController(ProductService productService, UserProductService userProductService) {
        this.productService = productService;
        this.userProductService = userProductService;
    }

    @GetMapping("/products/{userId}")
    public Set<Product> productsByUserId(@PathVariable Long userId) {
        return userProductService.getProductsByUserId(userId);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> productById(@PathVariable Long productId) {
        Optional<Product> product = productService.get(productId);
        return ResponseEntity
                .status(200)
                .body(product.orElseThrow());
    }
}

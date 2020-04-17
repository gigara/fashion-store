package com.giga.FashionStore.controller;

import com.giga.FashionStore.repository.ProductRepository;
import com.giga.FashionStore.response.ProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * route controller for non authenticated requests.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/open")
public class OpenController {
    @Autowired
    ProductRepository productRepository;

    /**
     * get products
     *
     * @param page number
     * @return products response as json
     */
    @GetMapping("/getproducts")
    public ResponseEntity<?> getProducts(@Valid @RequestParam(value = "page", defaultValue = "1") int page) {
        List<ProductsResponse> products = productRepository.getProducts(PageRequest.of(page - 1, 10));
        return ResponseEntity.ok(products);
    }

}

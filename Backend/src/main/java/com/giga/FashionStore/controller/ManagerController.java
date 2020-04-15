package com.giga.FashionStore.controller;

import com.giga.FashionStore.model.Category;
import com.giga.FashionStore.model.Product;
import com.giga.FashionStore.repository.CategoryRepository;
import com.giga.FashionStore.repository.ProductRepository;
import com.giga.FashionStore.request.AddProductRequest;
import com.giga.FashionStore.response.MessageResponse;
import com.giga.FashionStore.service.SequenceGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * route controller for store manager's task requests.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SequenceGenerateService sequenceGenerateService;

    // add product
    @PostMapping("/addproduct")
    @PostAuthorize("hasRole('STORE_MANAGER')")
    public ResponseEntity<?> addProduct(@Valid @RequestBody AddProductRequest request) {

        Product product = new Product(sequenceGenerateService.generateSequence(Product.SEQUENCE_NAME),
                request.getProdName(),
                request.getProdDescription(),
                request.getProdPrice());

        Category category = categoryRepository.findById(request.getProdCategory()).
                orElseThrow(() -> new ProviderNotFoundException("Category Not Found"));
        product.setProdCategory(category);

        productRepository.save(product);

        return ResponseEntity.ok(new MessageResponse("Product added successfully!"));
    }
}

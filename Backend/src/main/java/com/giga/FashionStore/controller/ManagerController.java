package com.giga.FashionStore.controller;

import com.giga.FashionStore.model.Category;
import com.giga.FashionStore.model.Discount;
import com.giga.FashionStore.model.Product;
import com.giga.FashionStore.repository.CategoryRepository;
import com.giga.FashionStore.repository.DiscountRepository;
import com.giga.FashionStore.repository.ProductRepository;
import com.giga.FashionStore.request.AddDiscountRequest;
import com.giga.FashionStore.request.AddProductRequest;
import com.giga.FashionStore.response.MessageResponse;
import com.giga.FashionStore.service.SequenceGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
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
    DiscountRepository discountRepository;
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

        Category category = categoryRepository.findById(request.getProdCategory()).orElse(null);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new MessageResponse("Category not found!"));
        }
        product.setProdCategory(category);

        productRepository.save(product);

        return ResponseEntity.ok(new MessageResponse("Product added successfully!"));
    }

    // add discounts to the product
    @PostMapping("/adddiscount")
    @PostAuthorize("hasRole('STORE_MANAGER')")
    public ResponseEntity<?> addDiscount(@Valid @RequestBody AddDiscountRequest request) {
        Product product = productRepository.findById(request.getProductId()).orElse(null);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new MessageResponse("Product not found!"));
        }

        Discount discount = new Discount(sequenceGenerateService.generateSequence(Discount.SEQUENCE_NAME),
                request.getDiscountName(), request.getDiscountValue());
        discountRepository.save(discount);
        product.setProdDiscount(discount);

        productRepository.save(product);
        return ResponseEntity.ok(new MessageResponse("Discount added successfully!"));
    }
}

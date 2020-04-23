package com.giga.FashionStore.controller;

import com.giga.FashionStore.model.Product;
import com.giga.FashionStore.model.Rating;
import com.giga.FashionStore.repository.ProductRepository;
import com.giga.FashionStore.response.MessageResponse;
import com.giga.FashionStore.response.ProductResponse;
import com.giga.FashionStore.response.ProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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

    /**
     * get product by Id
     *
     * @param prod_Id Product Id
     * @return products response as json
     */
    @GetMapping("/getproduct")
    public ResponseEntity<?> getProduct(@Valid @RequestParam(value = "prod_Id") String prod_Id) {
        Product product = productRepository.findById(prod_Id).orElse(null);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new MessageResponse("Product not found!"));
        }

        // average rating
        List<Rating> ratings = product.getProdRatings();
        double avg = 0;
        if (ratings.size() > 0) {
            for (Rating prodRating : ratings) {
                avg += prodRating.getRating_rate();
            }
            avg /= ratings.size();
        }
        ProductResponse productResponse = new ProductResponse(product.getProd_id(),
                product.getProdName(),
                product.getProdDescription(),
                product.getProdPrice(),
                product.getProdComments(),
                avg);

        productResponse.setAverageRating(avg);
        return ResponseEntity.ok(productResponse);
    }


}

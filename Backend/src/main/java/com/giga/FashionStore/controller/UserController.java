package com.giga.FashionStore.controller;

import com.giga.FashionStore.model.Product;
import com.giga.FashionStore.model.SiteUser;
import com.giga.FashionStore.repository.ProductRepository;
import com.giga.FashionStore.repository.UserRepository;
import com.giga.FashionStore.request.AddToWishListRequest;
import com.giga.FashionStore.response.MessageResponse;
import com.giga.FashionStore.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * route controller for admin's task requests.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @PostMapping("/addtowishlist")
    @PostAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addToWishList(@Valid @RequestBody AddToWishListRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        SiteUser siteUser = (SiteUser) userRepository.findById(request.getUser_Id()).orElse(null);

        if (siteUser == null || !username.equals(siteUser.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new MessageResponse("User not found!"));
        }

        Product product = productRepository.findById(request.getProd_Id()).orElse(null);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new MessageResponse("Product not found!"));
        }

        Set<Product> products = new HashSet<>(siteUser.getWishList());
        products.add(product);
        siteUser.setWishList(products);
        userRepository.save(siteUser);

        return ResponseEntity.ok(new MessageResponse("Product has been successfully added to the wishlist"));
    }
}

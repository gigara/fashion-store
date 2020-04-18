package com.giga.FashionStore.controller;

import com.giga.FashionStore.model.Comment;
import com.giga.FashionStore.model.Product;
import com.giga.FashionStore.model.Rating;
import com.giga.FashionStore.model.SiteUser;
import com.giga.FashionStore.repository.CommentRepository;
import com.giga.FashionStore.repository.ProductRepository;
import com.giga.FashionStore.repository.RatingRepository;
import com.giga.FashionStore.repository.UserRepository;
import com.giga.FashionStore.request.AddCommentRequest;
import com.giga.FashionStore.request.AddToWishListRequest;
import com.giga.FashionStore.response.MessageResponse;
import com.giga.FashionStore.service.SequenceGenerateService;
import com.giga.FashionStore.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * route controller for user's task requests.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    SequenceGenerateService sequenceGenerateService;

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

    @PostMapping("/addComment")
    @PostAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addComment(@Valid @RequestBody AddCommentRequest request) {
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

        if (request.getComment() != null) {
            Comment comment = new Comment(sequenceGenerateService.generateSequence(Comment.SEQUENCE_NAME),
                    request.getComment(), new Date(), siteUser);
            commentRepository.save(comment);

            List<Comment> comments = new ArrayList<>(product.getProdComments());
            comments.add(comment);
            product.setProdComments(comments);
        }
        if (request.getRating() > 0) {
            Rating rating = new Rating(sequenceGenerateService.generateSequence(Rating.SEQUENCE_NAME),
                    request.getRating(), siteUser);
            Rating previousRate = ratingRepository.findByUser(siteUser).orElse(null);
            if (previousRate != null) {
                previousRate.setRating_rate(rating.getRating_rate());
                previousRate.setUser(siteUser);
                ratingRepository.save(previousRate);
            } else {
                ratingRepository.save(rating);
            }

            List<Rating> ratings = new ArrayList<>(product.getProdRatings());
            if (previousRate == null) {
                ratings.add(rating);
            }
            product.setProdRatings(ratings);
        }
        productRepository.save(product);

        return ResponseEntity.ok(new MessageResponse("Comment has been successfully added"));
    }
}

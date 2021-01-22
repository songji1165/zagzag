package com.jtrio.zagzag.review;

import com.jtrio.zagzag.liker.LikerService;
import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final LikerService likerService;

    @GetMapping("/product/{productId}")
    public Page<ReviewDto> getProductReviews(
            @PathVariable Long productId,
            @AuthenticationPrincipal SecurityUser securityUser,
            @PageableDefault Pageable pageable
    ) {
        return reviewService.getProductReviews(productId, securityUser, pageable);
    }

    @PostMapping
    public ReviewDto createReview(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody ReviewCommand.createReview review
    ) {
        return reviewService.createReview(securityUser, review);
    }

    @PutMapping("likers/{id}")
    public Long updateLiker(
            @PathVariable Long id,
            @AuthenticationPrincipal SecurityUser securityUser
    ) {
        return likerService.updateLiker(id, securityUser);
    }
}

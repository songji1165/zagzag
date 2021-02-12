package com.jtrio.zagzag.review;

import com.jtrio.zagzag.liker.LikerService;
import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final LikerService likerService;

    @PostMapping
    public ReviewDto createReview(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody ReviewCommand.CreateReview review) {
        return reviewService.createReview(securityUser, review);
    }

    @PutMapping("/{id}")
    public ReviewDto updateReview(
            @AuthenticationPrincipal SecurityUser securityUser,
            @PathVariable Long id,
            @RequestBody ReviewCommand.UpdateReview review){
        return reviewService.updateReview(securityUser, id, review);
    }

    @PutMapping("likers/{id}")
    public Long updateLiker(
            @PathVariable Long id,
            @AuthenticationPrincipal SecurityUser securityUser) {
        return likerService.updateLiker(id, securityUser);
    }
}

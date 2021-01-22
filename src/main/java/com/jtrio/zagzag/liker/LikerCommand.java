package com.jtrio.zagzag.liker;

import com.jtrio.zagzag.model.Liker;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LikerCommand {
    @NotBlank
    private Long userId;
    @NotBlank(message = "리뷰를 선택해주세요.")
    private Review review;

    public static Liker toLiker(User user, Review review) {
        Liker liker = new Liker();
        liker.setUser(user);
        liker.setReview(review);
        return liker;
    }
}

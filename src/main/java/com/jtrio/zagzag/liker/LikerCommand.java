package com.jtrio.zagzag.liker;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Liker;
import com.jtrio.zagzag.model.Review;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LikerCommand {
    @NotBlank
    private Long userId;
    @NotBlank(message = "리뷰를 선택해주세요.")
    private Review review;

    public static Liker toLiker (Long userId, Review review){
        Liker liker = new Liker();
        liker.setUserId(userId);
        liker.setReview(review);
        return liker;
    }
}

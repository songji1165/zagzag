package com.jtrio.zagzag.utils.common;

import com.jtrio.zagzag.model.Review;

import java.util.List;

public class CommonUtils {

    public byte averageScore(List<Review> reviews) {
        int sumScore = 0;

        for(Review review : reviews){
            byte productScore = review.getProductScore();
            sumScore += productScore;
        }

        int prodcutScore = sumScore/reviews.size();

        return (byte) prodcutScore;
    }
}

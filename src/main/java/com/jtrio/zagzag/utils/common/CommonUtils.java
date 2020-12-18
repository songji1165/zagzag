package com.jtrio.zagzag.utils.common;

import com.jtrio.zagzag.model.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtils {

    public HashMap<String, Byte> averageScore(List<Review> reviews) {
        int sumProductScore = 0;
        int sumDeliveryScore = 0;

        for(Review review : reviews){
            byte productScore = review.getProductScore();
            byte deliveryScore = review.getDeliveryScore();
            sumProductScore += productScore;
            sumDeliveryScore += deliveryScore;
        }

        int prodcutScore = sumProductScore/reviews.size();
        int deliveryScore = sumDeliveryScore/reviews.size();

        HashMap<String, Byte> score = new HashMap<>();

        score.put("product",(byte) prodcutScore);
        score.put("delivery",(byte) deliveryScore);
        return score;
    }
}

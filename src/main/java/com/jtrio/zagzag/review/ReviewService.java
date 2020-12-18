package com.jtrio.zagzag.review;

import com.jtrio.zagzag.category.CategoryRepository;
import com.jtrio.zagzag.exception.DuplicateReviewException;
import com.jtrio.zagzag.exception.NonPurchaseException;
import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.*;
import com.jtrio.zagzag.order.OrderRepository;
import com.jtrio.zagzag.product.ProductCommand;
import com.jtrio.zagzag.product.ProductDto;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.product.ProductService;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.RuntimeMBeanException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewDto createReview(ReviewCommand.createReview reviewCommand){
        /**
         * 1. user Security
         * 2. product 확인
         * 3. user가 구매자인지 확인
         * */
        User user = userRepository.findByEmail(reviewCommand.getUserEmail()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        Product product = productRepository.findById(reviewCommand.getProductId()).orElseThrow(()->new NotFoundException("존재하지 않는 상품입니다."));


        List<ProductOrder> order = orderRepository.findByUserAndProduct(user, product);
        if(order.size() > 0){ //사용자가 상품을 주문한 이력이 있다.

            if(reviewRepository.existsByUserAndProduct(user, product)){//리뷰는 주문 한 개당 한 개만
                throw new DuplicateReviewException("리뷰는 한 개만 입력할 수 있습니다.");
            }

            Review review = reviewCommand.toReview(user, product);
            reviewRepository.save(review);

            //리뷰 완료되면, 상품의 score 업데이트하기
            productService.updateScore(review.getProduct());

            return review.toReviewDto(reviewCommand.getUserEmail());

        }else{
            throw new NonPurchaseException("구매한 상품에만 리뷰를 입력할 수 있습니다.");
        }
    }
}

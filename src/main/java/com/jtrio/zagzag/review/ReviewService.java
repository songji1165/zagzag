package com.jtrio.zagzag.review;

import com.jtrio.zagzag.category.CategoryRepository;
import com.jtrio.zagzag.exception.DuplicateReviewException;
import com.jtrio.zagzag.exception.FailedChangeException;
import com.jtrio.zagzag.exception.NonPurchaseException;
import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.*;
import com.jtrio.zagzag.order.OrderDto;
import com.jtrio.zagzag.order.OrderRepository;
import com.jtrio.zagzag.product.ProductCommand;
import com.jtrio.zagzag.product.ProductDto;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.product.ProductService;
import com.jtrio.zagzag.security.SecurityUser;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Page<ReviewDto> getProductReviews(Long id, SecurityUser securityUser, Pageable pageable){
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 상품 찾을 수 없습니다."));
        // 해당 상품의 전체 리뷰 찾기
        Page<Review> productReviews = reviewRepository.findByProduct(product, pageable);

        if(securityUser == null){ // 비회원
            return productReviews.map(review -> ReviewDto.toReviewDto(review));
        }else{ //회원인증
            return productReviews.map(review -> ReviewDto.toReviewDto(review, securityUser.getUser()));
        }
    }

    @Transactional
    public ReviewDto createReview(SecurityUser securityUser, ReviewCommand.createReview reviewCommand){
        /**
         * 1. user Security
         * 2. product 확인
         * 3. user가 구매자인지 확인
         * */
        User user = securityUser.getUser();
        Product product = productRepository.findById(reviewCommand.getProductId()).orElseThrow(()->new NotFoundException("존재하지 않는 상품입니다."));
        ProductOrder order = orderRepository.findById(reviewCommand.getOrderId()).orElseThrow(()->new NonPurchaseException("주문번호를 확인해주세요."));

        List<ProductOrder> orders = orderRepository.findByUserAndProduct(user, product); // 주문리스트 (사용자, 상품 조회)
        List<Review> reviews = reviewRepository.findByUserAndProduct(user, product);  // 리뷰리스트

        if(reviewRepository.existsByOrder(order)){//리뷰는 주문 한개 당 리뷰 한 개
            throw new DuplicateReviewException("리뷰는 주문 건당 한 개만 입력할 수 있습니다.");
        }

        Review review = reviewCommand.toReview(user, product, order);
        reviewRepository.save(review);

        //리뷰 완료되면, 상품의 score 업데이트하기
        productService.updateScore(review.getProduct());

        return ReviewDto.toReviewDto(review);
    }

    @Transactional
    public int updateLiker(Long id, SecurityUser securityUser){
        User user = securityUser.getUser();
        Review review = reviewRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 리뷰를 찾을 수 없습니다."));

        List<User> likers = review.getLikers();

        for(User likedUser : likers){
            if(likedUser.equals(user)){
                throw new FailedChangeException("이미 추천한 구매후기입니다.");
            }
        }

        likers.add(user);
        review.setLikers(likers);
        reviewRepository.save(review);

        return review.getLikers().size();
    }
}

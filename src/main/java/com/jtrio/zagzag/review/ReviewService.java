package com.jtrio.zagzag.review;

import com.jtrio.zagzag.exception.DuplicateDataException;
import com.jtrio.zagzag.exception.NonPurchaseException;
import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.exception.NotMatchUserException;
import com.jtrio.zagzag.liker.LikerRepository;
import com.jtrio.zagzag.model.*;
import com.jtrio.zagzag.order.OrderRepository;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.product.ProductService;
import com.jtrio.zagzag.security.SecurityUser;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final LikerRepository likerRepository;

    public Page<ReviewDto> getProductReviews(Long id, SecurityUser securityUser, Pageable pageable){
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 상품 찾을 수 없습니다."));
        // 해당 상품의 전체 리뷰 찾기
        Page<Review> productReviews = reviewRepository.findByProduct(product, pageable);

        if(securityUser == null){ // 비회원
            return productReviews.map(review -> {
                Long likers = likerRepository.countByReview(review);
                return ReviewDto.toReviewDto(review, likers);
            });
        }else{ //회원인증
            User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
            String email = user.getEmail();
            Long userId = user.getId();

            // 나의 리뷰인지, 내가 좋아요 했는지 ....
            return productReviews.map(review -> {
                        Long likers = likerRepository.countByReview(review);
                        boolean liked = likerRepository.existsByUserIdAndReview(user.getId(), review);
                        return ReviewDto.toReviewDto(
                            review,
                            likers,
                            email,
                            liked);
                });
        }
    }

    @Transactional
    public ReviewDto createReview(SecurityUser securityUser, ReviewCommand.createReview reviewCommand){
        User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        Product product = productRepository.findById(reviewCommand.getProductId()).orElseThrow(()->new NotFoundException("존재하지 않는 상품입니다."));
        ProductOrder order = orderRepository.findById(reviewCommand.getOrderId()).orElseThrow(()->new NonPurchaseException("주문번호를 확인해주세요."));

        if(!order.getUser().equals(user)) throw new NotMatchUserException("주문건과 사용자의 정보가 일치하지 않습니다.");

        if(!product.getId().equals(order.getProduct().getId())) throw new NonPurchaseException("주문한 상품이 맞는지 확인해주세요. 리뷰는 상품을 주문한 사용자만 입력할 수 있습니");

        if(reviewRepository.existsByOrder(order)) throw new DuplicateDataException("리뷰는 주문 건당 한 개만 입력할 수 있습니다.");

        Review review = reviewCommand.toReview(user, product, order);
        reviewRepository.save(review);

        //리뷰 완료되면, 상품의 score 업데이트하기
        productService.updateScore(review.getProduct());

        return ReviewDto.toReviewDto(review, 0L, user.getEmail(), false);
    }

}

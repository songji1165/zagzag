package com.jtrio.zagzag.liker;

import com.jtrio.zagzag.exception.DuplicateDataException;
import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.Liker;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.review.ReviewRepository;
import com.jtrio.zagzag.security.SecurityUser;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikerService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final LikerRepository likerRepository;

    @Transactional
    public int updateLiker(Long id, SecurityUser securityUser){
        User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        Long userId = user.getId();
        Review review = reviewRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 리뷰를 찾을 수 없습니다."));

        Long likers = likerRepository.countByUserIdAndReview(userId, review);
        if(likers > 0) throw new DuplicateDataException("좋아요는 리뷰 한 개당 한 번만 가능합니다.");

        Liker liker = LikerCommand.toLiker(userId, review);
        likerRepository.save(liker);

        return review.getLikers().size();
    }
}

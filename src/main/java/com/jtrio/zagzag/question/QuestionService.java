package com.jtrio.zagzag.question;

import com.jtrio.zagzag.comment.CommentRepository;
import com.jtrio.zagzag.exception.FailedChangeException;
import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.Question;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.order.OrderRepository;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.security.SecurityUser;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public QuestionDto createQuestion(SecurityUser securityUser, QuestionCommand.CreateQuestionCommand questionCommand){
        User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        Product product = productRepository.findById(questionCommand.getProductId()).orElseThrow(() -> new NotFoundException("해당 상품 찾을 수 없습니다."));

        boolean order = orderRepository.existsByUserAndProduct(user, product);
        Question question = questionCommand.toQuestion(user, product, order);
        questionRepository.save(question);

        Long comments = commentRepository.countByQuestion(question);
        return QuestionDto.toQuestionDto(question, comments, user);
    }

    public Page<QuestionDto> getProductQuestions(Long id, SecurityUser securityUser, Pageable pageable){

        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 상품 찾을 수 없습니다."));
        Page<Question> questions = questionRepository.findByProduct(product, pageable);

        if(securityUser == null){// 비회원
            return questions.map(question -> {
                Long comments = commentRepository.countByQuestion(question);
                return QuestionDto.toQuestionDto(question, comments);
            });
        }else{ //회원
            User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));

            return questions.map(question -> {
                Long comments = commentRepository.countByQuestion(question);
                return QuestionDto.toQuestionDto(question, comments, user);
            });
        }
    }

    @Transactional
    public QuestionDto updateQuestion(Long id, SecurityUser securityUser, QuestionCommand.updateQuestionCommand updateQuestionCommand){
        User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        Question question = questionRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 리뷰 찾을 수 없습니다."));

        if(user.equals(question.getUser())){
            updateQuestionCommand.toQuestion(question);
            questionRepository.save(question);
            Long comments = commentRepository.countByQuestion(question);

            return QuestionDto.toQuestionDto(question, comments, user);
        }else{
            throw new FailedChangeException("리뷰를 작성한 사용자만 수정 가능합니다.");
        }
    }

}

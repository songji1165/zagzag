package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.exception.FailedChangeException;
import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.Comment;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.Question;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.order.OrderDto;
import com.jtrio.zagzag.order.OrderRepository;
import com.jtrio.zagzag.question.QuestionDto;
import com.jtrio.zagzag.question.QuestionRepository;
import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final QuestionRepository questionRepository;
    private final OrderRepository orderRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentDto createComment(SecurityUser securityUser, CommentCommand commentCommand){
        User user = securityUser.getUser();
        Question question = questionRepository.findById(commentCommand.getQuestionId()).orElseThrow(() -> new NotFoundException("해당 질문 찾을 수 없습니다."));
        List<ProductOrder> order = orderRepository.findByUserAndProduct(user, question.getProduct());

        if(question.getSecret() == false){
            Comment comment = commentCommand.toComment(user, question, order);
            commentRepository.save(comment);

            List<Comment> questionComment = question.getComments();

            questionComment.add(comment);
            question.setComments(questionComment);

            questionRepository.save(question);

            return CommentDto.toCommentDto(comment, user);
        }else{
            throw new FailedChangeException("비밀글에는 댓글을 달 수 없습니다.");
        }
    }

    public List<CommentDto> getQuestionComments(Long id, SecurityUser securityUser){
        Question question = questionRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 질문 찾을 수 없습니다."));
        List<Comment> questionComments = question.getComments();
        List<CommentDto> commentDtos = new ArrayList<>();

        if(securityUser == null){ // 비회원
            for(Comment comment : questionComments){
                commentDtos.add(CommentDto.toCommentDto(comment));
            }
        }else{
            User user = securityUser.getUser();
            for(Comment comment : questionComments){
                commentDtos.add(CommentDto.toCommentDto(comment, user));
            }
        }

        return commentDtos;
    }
}

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
import com.jtrio.zagzag.user.UserRepository;
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
    private final UserRepository userRepository;

    @Transactional
    public CommentDto createComment(SecurityUser securityUser, CommentCommand.CreateComment commentCommand) {
        User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        Question question = questionRepository.findById(commentCommand.getQuestionId()).orElseThrow(() -> new NotFoundException("해당 질문 찾을 수 없습니다."));
        boolean buyer = orderRepository.existsByUserAndProduct(user, question.getProduct());

        if (!question.getSecret()) {
            Comment comment = commentCommand.toComment(user, question, buyer);
            commentRepository.save(comment);

            return CommentDto.toCommentDto(comment, user);
        } else {
            if (question.getUser().equals(user)) {
                Comment comment = commentCommand.toComment(user, question, buyer);
                commentRepository.save(comment);

                return CommentDto.toCommentDto(comment, user);
            } else {
                throw new FailedChangeException("비밀글에는 질문 작성자만 댓글을 달 수 있습니다.");
            }
        }
    }

    public List<CommentDto> getQuestionComments(Long id, SecurityUser securityUser) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 질문 찾을 수 없습니다."));
        List<Comment> questionComments = commentRepository.findByQuestion(question);
        List<CommentDto> commentsDto = new ArrayList<>();

        if (securityUser == null) { // 비회원
            for (Comment comment : questionComments) {
                CommentDto commentDto = CommentDto.toCommentDto(comment);
                commentsDto.add(commentDto);
            }
        } else {
            User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
            for (Comment comment : questionComments) {
                CommentDto commentDto = CommentDto.toCommentDto(comment, user);
                commentsDto.add(commentDto);
            }
        }

        return commentsDto;
    }

    @Transactional
    public CommentDto updateComment(Long id, CommentCommand.UpdateComment commentCommand, SecurityUser securityUser) {
        User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 댓글을 찾을 수 없습니다."));

        if (comment.getUser().equals(user)) {
            commentCommand.toComment(comment);
            commentRepository.save(comment);

            return CommentDto.toCommentDto(comment, user);
        } else {
            throw new FailedChangeException("댓글 작성자만 댓글을 수정할 수 있습니다.");
        }
    }
}

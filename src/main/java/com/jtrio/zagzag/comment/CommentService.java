package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.exception.FailedChangeException;
import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.Comment;
import com.jtrio.zagzag.model.Question;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.order.OrderRepository;
import com.jtrio.zagzag.question.QuestionRepository;
import com.jtrio.zagzag.security.SecurityUser;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final QuestionRepository questionRepository;
    private final OrderRepository orderRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentDto createComment(Long id, SecurityUser securityUser, CommentCommand.CreateComment commentCommand) {
        User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        Question question = questionRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 질문 찾을 수 없습니다."));
        boolean buyer = orderRepository.existsByUserAndProduct(user, question.getProduct());

        if (question.getSecret() && !question.getUser().equals(user)) {
            throw new FailedChangeException("비밀글에는 질문 작성자만 댓글을 달 수 있습니다.");
        }

        Comment comment = commentCommand.toComment(user, question, buyer);
        commentRepository.save(comment);

        return CommentDto.toCommentDto(comment, user);
    }

    public List<CommentDto> getQuestionComments(Long id, SecurityUser securityUser) {
        User user = securityUser != null ?
                userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다.")) :
                null;
        Question question = questionRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 질문 찾을 수 없습니다."));
        List<Comment> questionComments = commentRepository.findByQuestion(question);

        return  questionComments.stream()
                .map(comment -> CommentDto.toCommentDto(comment, user))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto updateComment(Long id, CommentCommand.UpdateComment commentCommand, SecurityUser securityUser) {
        User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 댓글을 찾을 수 없습니다."));

        if (!comment.getUser().equals(user)) { throw new FailedChangeException("댓글 작성자만 댓글을 수정할 수 있습니다."); }

        commentCommand.toComment(comment);
        commentRepository.save(comment);

        return CommentDto.toCommentDto(comment, user);
    }
}

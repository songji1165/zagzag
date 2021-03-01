package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.enums.CommenterType;
import com.jtrio.zagzag.enums.MessageStatus;
import com.jtrio.zagzag.model.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;

public class CommentCommand {
    @Data
    public static class CreateComment {
        @NotBlank(message = "내용을 입력해주세요.")
        private String content;
        private MessageStatus status = MessageStatus.NORMAL;

        public Comment toComment(User user, Question question, boolean buyer) {
            Comment comment = new Comment();

            comment.setContent(content);
            comment.setStatus(status);
            comment.setSecret(question.getSecret());//문의글의 비밀글 여부를 따른다.
            comment.setUser(user);
            comment.setQuestion(question);
            comment.setType(buyer ? CommenterType.BUYER : CommenterType.NON_BUYER);

            return comment;
        }
    }

    @Data
    public static class UpdateComment {
        @NotBlank(message = "내용을 입력해주세요.")
        private String content;
        private MessageStatus status;

        public Comment toComment(Comment comment) {
            comment.setContent(content);
            if (status != null) { comment.setStatus(status); }

            return comment;
        }
    }

}

package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.enums.CommenterType;
import com.jtrio.zagzag.enums.MessageStatus;
import com.jtrio.zagzag.model.*;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.security.MessageDigest;
import java.util.List;

@Data
public class CommentCommand {
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    private MessageStatus status = MessageStatus.NORMAL;
    private Boolean secret = false;
    @NotBlank(message = "질문을 선택해주세요.")
    private Long questionId;

    public Comment toComment(User user, Question question, boolean buyer) {
        Comment comment = new Comment();

        comment.setContent(content);
        comment.setStatus(status);
        comment.setSecret(secret);
        comment.setUser(user);
        comment.setQuestion(question);

        if (buyer) { //order가 있으면 바이어
            comment.setType(CommenterType.BUYER);
        } else {
            comment.setType(CommenterType.NON_BUYER);
        }

        return comment;
    }

}

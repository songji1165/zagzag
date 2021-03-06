package com.jtrio.zagzag.question;

import com.jtrio.zagzag.enums.CommenterType;
import com.jtrio.zagzag.enums.MessageStatus;
import com.jtrio.zagzag.model.Question;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.utils.ContentUtils;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuestionDto {
    private Long id;
    private Long productId;
    private String content;
    private CommenterType type;
    private MessageStatus status;
    private Boolean secret;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Long comments; //더보기 누른 경우, 해당 질문의 답변 GET
    private String email;
    private Boolean myQuestion = false;

    public static QuestionDto toQuestionDto(Question question, Long comments, User user) {
        QuestionDto questionDto = new QuestionDto();

        questionDto.setId(question.getId());
        questionDto.setProductId(question.getProduct().getId());
        questionDto.setType(question.getType());
        questionDto.setStatus(question.getStatus());
        questionDto.setSecret(question.getSecret());
        questionDto.setCreated(question.getCreated());
        questionDto.setComments(comments);

        User questionUser = question.getUser();
        questionDto.setEmail(questionUser.getEmail());
        questionDto.setMyQuestion(questionUser.equals(user));
        String contentDto = ContentUtils.setCommentDto(
                questionUser.equals(user) ? false : question.getSecret(),
                question.getStatus(),
                question.getContent()
        );
        questionDto.setContent(contentDto);

        return questionDto;
    }
}

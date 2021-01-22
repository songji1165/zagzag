package com.jtrio.zagzag.question;

import com.jtrio.zagzag.enums.CommenterType;
import com.jtrio.zagzag.enums.MessageStatus;
import com.jtrio.zagzag.model.Question;
import com.jtrio.zagzag.model.User;
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

    public static QuestionDto toQuestionDto(Question question, Long comments) {
        QuestionDto questionDto = new QuestionDto();

        questionDto.setId(question.getId());
        questionDto.setProductId(question.getProduct().getId());
        questionDto.setContent(question.getContent());
        questionDto.setType(question.getType());
        questionDto.setStatus(question.getStatus());
        questionDto.setSecret(question.getSecret());
        questionDto.setCreated(question.getCreated());
        questionDto.setUpdated(question.getUpdated());
        questionDto.setComments(comments);
        questionDto.setEmail(question.getUser().getEmail());

        return questionDto;
    }

    public static QuestionDto toQuestionDto(Question question, Long comments, User user) {
        QuestionDto questionDto = new QuestionDto();

        questionDto.setId(question.getId());
        questionDto.setProductId(question.getProduct().getId());
        questionDto.setContent(question.getContent());
        questionDto.setType(question.getType());
        questionDto.setStatus(question.getStatus());
        questionDto.setSecret(question.getSecret());
        questionDto.setCreated(question.getCreated());
        questionDto.setComments(comments);

        User questionUser = question.getUser();
        questionDto.setEmail(questionUser.getEmail());
        if (questionUser.equals(user)) {
            questionDto.setMyQuestion(true);
        }

        return questionDto;
    }
}

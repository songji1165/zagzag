package com.jtrio.zagzag.question;

import com.jtrio.zagzag.enums.CommenterType;
import com.jtrio.zagzag.enums.MessageStatus;
import com.jtrio.zagzag.model.Question;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import com.sun.source.doctree.CommentTree;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuestionDto {
    private Long id;
    private Long productId;
    private String content;
    private CommenterType type;
    private MessageStatus status;
    private Boolean secret;
    private LocalDateTime created;
    private Integer comments; //더보기 누른 경우, 해당 질문의 답변 GET
    private String email;
    private Boolean myQuestion = false;

    public static QuestionDto toQuestionDto(Question question){
        QuestionDto questionDto = new QuestionDto();

        questionDto.setId(question.getId());
        questionDto.setProductId(question.getProduct().getId());
        questionDto.setContent(question.getContent());
        questionDto.setType(question.getType());
        questionDto.setStatus(question.getStatus());
        questionDto.setSecret(question.getSecret());
        questionDto.setCreated(question.getCreated());
        questionDto.setComments(question.getComments().size());
        questionDto.setEmail(question.getUser().getEmail());

        return questionDto;
    }

    public static QuestionDto toQuestionDto(Question question, User user){
        QuestionDto questionDto = new QuestionDto();

        questionDto.setId(question.getId());
        questionDto.setProductId(question.getProduct().getId());
        questionDto.setContent(question.getContent());
        questionDto.setType(question.getType());
        questionDto.setStatus(question.getStatus());
        questionDto.setSecret(question.getSecret());
        questionDto.setCreated(question.getCreated());
        questionDto.setComments(question.getComments().size());

        User questionUser = question.getUser();
        questionDto.setEmail(questionUser.getEmail());
        if(questionUser.equals(user)){
            questionDto.setMyQuestion(true);
        }

        return questionDto;
    }
}

package com.jtrio.zagzag.question;

import com.jtrio.zagzag.enums.CommenterType;
import com.jtrio.zagzag.enums.MessageStatus;
import com.jtrio.zagzag.model.*;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class QuestionCommand {
    @Data
    public static class CreateQuestionCommand{
        @NotBlank(message = "내용을 입력해주세요.")
        private String content;
        private MessageStatus status = MessageStatus.NORMAL;
        private Boolean secret = false;
        @NotBlank(message = "상품을 선택해주세요")
        private Long productId;

        public Question toQuestion(User user, Product product, boolean buyer){
            Question question = new Question();

            question.setContent(content);
            question.setStatus(status);
            question.setSecret(secret);
            question.setUser(user);
            question.setProduct(product);

            if(buyer) {
                question.setType(CommenterType.BUYER);
            }else{
                question.setType(CommenterType.NON_BUYER);
            }

            return question;
        }
    }

    @Data
    public static class updateQuestionCommand{
        private String content;
        private Boolean secret;

        public Question toQuestion(Question question){
            if(content != null){
                question.setContent(content);
            }

            if(secret != null && !secret.equals(question.getSecret())){
                question.setSecret(secret);
            }

            return question;
        }
    }
}

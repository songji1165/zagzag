package com.jtrio.zagzag.question;

import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public QuestionDto createQuestion(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody QuestionCommand.CreateQuestionCommand questionCommand
    ) {
        return questionService.createQuestion(securityUser, questionCommand);
    }

    @GetMapping("/product/{productId}")
    public Page<QuestionDto> getProductQuestions(
            @PathVariable Long productId,
            @AuthenticationPrincipal SecurityUser securityUser,
            @PageableDefault() Pageable pageable
    ) {
        return questionService.getProductQuestions(productId, securityUser, pageable);
    }

    @PutMapping("/{id}")
    public QuestionDto updateQuestion(
            @PathVariable Long id,
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody QuestionCommand.updateQuestionCommand updateQuestionCommand
    ) {
        return questionService.updateQuestion(id, securityUser, updateQuestionCommand);
    }

}

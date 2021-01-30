package com.jtrio.zagzag.question;

import com.jtrio.zagzag.comment.CommentDto;
import com.jtrio.zagzag.comment.CommentService;
import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final CommentService commentService;

    @PostMapping
    public QuestionDto createQuestion(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody QuestionCommand.CreateQuestion questionCommand) {
        return questionService.createQuestion(securityUser, questionCommand);
    }

    @PutMapping("/{id}")
    public QuestionDto updateQuestion(
            @PathVariable Long id,
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody QuestionCommand.UpdateQuestion QuestionCommand) {
        return questionService.updateQuestion(id, securityUser, QuestionCommand);
    }

    @GetMapping("/{id}/comments")
    public List<CommentDto> getQuestionComments(
            @PathVariable Long id,
            @AuthenticationPrincipal SecurityUser securityUser) {
        return commentService.getQuestionComments(id, securityUser);
    }

}

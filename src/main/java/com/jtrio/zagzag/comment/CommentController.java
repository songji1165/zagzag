package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.question.QuestionCommand;
import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentDto createComment(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody CommentCommand commentCommand
            ){
        return commentService.createComment(securityUser, commentCommand);
    }

    @GetMapping("/question/{id}")
    public List<CommentDto> getQuestionComments(
            @PathVariable Long id,
            @AuthenticationPrincipal SecurityUser securityUser
            ){
        return commentService.getQuestionComments(id, securityUser);
    }
}
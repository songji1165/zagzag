package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentDto createComment(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody CommentCommand.CreateComment commentCommand) {
        return commentService.createComment(securityUser, commentCommand);
    }

    @PutMapping("/{id}")
    public CommentDto updateComments(
            @PathVariable Long id,
            @RequestBody CommentCommand.UpdateComment commentCommand,
            @AuthenticationPrincipal SecurityUser securityUser) {
        return commentService.updateComment(id, commentCommand, securityUser);
    }

}

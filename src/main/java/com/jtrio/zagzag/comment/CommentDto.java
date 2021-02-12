package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.enums.CommenterType;
import com.jtrio.zagzag.enums.MessageStatus;
import com.jtrio.zagzag.model.Comment;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.utils.ContentUtils;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long id;
    private String content;
    private CommenterType type;
    private MessageStatus status;
    private Boolean secret;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String email;
    private Boolean myComment = false;

    public static CommentDto toCommentDto(Comment comment, User user) {
        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setType(comment.getType());
        commentDto.setStatus(comment.getStatus());
        commentDto.setSecret(comment.getSecret());
        commentDto.setCreated(comment.getCreated());

        User commentUser = comment.getUser();
        commentDto.setEmail(commentUser.getEmail());
        commentDto.setMyComment(commentUser.equals(user));

        String content = ContentUtils.setCommentDto(
                comment.getUser().equals(user) ? false : comment.getSecret(),
                comment.getStatus(),
                comment.getContent()
        );
        commentDto.setContent(content);

        return commentDto;
    }

}

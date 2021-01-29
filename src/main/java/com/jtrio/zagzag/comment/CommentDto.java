package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.enums.CommenterType;
import com.jtrio.zagzag.enums.MessageStatus;
import com.jtrio.zagzag.model.Comment;
import com.jtrio.zagzag.model.User;
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
        commentDto.setMyComment(commentUser.equals(user) ? true : false);
        String contentDto = setCommentDto(
                comment.getUser().equals(user) ? false : comment.getSecret(),
                comment.getStatus(),
                comment.getContent()
        );
        commentDto.setContent(contentDto);

        return commentDto;
    }

    private static String setCommentDto(Boolean secret, MessageStatus status, String comment) {
        String result = secret ? "해당글은 비밀글입니다." : comment;

        switch (status) {
            case DELETE:
                result = status.DELETE.getName();
                break;
            case BLOCK:
                result = status.BLOCK.getName();
                break;
            default:
                break;
        }

        return result;
    }
}

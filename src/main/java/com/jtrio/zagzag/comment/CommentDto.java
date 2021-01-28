package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.enums.CommenterType;
import com.jtrio.zagzag.enums.MessageStatus;
import com.jtrio.zagzag.model.Comment;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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

    public static CommentDto toCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setType(comment.getType());
        commentDto.setStatus(comment.getStatus());
        commentDto.setSecret(comment.getSecret());
        commentDto.setCreated(comment.getCreated());
        commentDto.setUpdated(comment.getUpdated());
        commentDto.setEmail(comment.getUser().getEmail());

        if (!comment.getSecret() && comment.getStatus().equals(MessageStatus.NORMAL))
            commentDto.setContent(comment.getContent());

        return commentDto;
    }

    public static CommentDto toCommentDto(Comment comment, User user) {
        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setType(comment.getType());
        commentDto.setStatus(comment.getStatus());
        commentDto.setSecret(comment.getSecret());
        commentDto.setCreated(comment.getCreated());
        commentDto.setEmail(comment.getUser().getEmail());

        /**
         *  1. secret = true :
         *      1) user = user : content O
         *      2) user != user : content X
         *
         *     secret = false :
         *          content O
         *
         *  2. status = Delete | Block :
         *      Content : X
         * */
        if (comment.getStatus() == MessageStatus.NORMAL) {
            if (comment.getUser().equals(user) || !comment.getSecret()) {
                commentDto.setContent(comment.getContent());
            }
        }

        if (comment.getUser().equals(user)) {
            commentDto.setMyComment(true);
        }

        return commentDto;
    }
}

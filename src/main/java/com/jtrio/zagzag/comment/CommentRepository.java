package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long id);

    List<Comment> findByQuestion(Question question);

    Long countByQuestion(Question question);

    Long countByQuestionAndUser(Question question, User user);

    @Modifying
    @Query(value = "UPDATE Comment a set a.secret = :secret where a.question = :question")
    public void updatesByCommentSecret(@Param("question") Question question, @Param("secret") boolean secret);
}

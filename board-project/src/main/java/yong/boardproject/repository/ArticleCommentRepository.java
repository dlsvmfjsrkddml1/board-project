package yong.boardproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yong.boardproject.domain.ArticleComment;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}

package yong.boardproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yong.boardproject.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}

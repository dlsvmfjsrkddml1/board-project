package yong.boardproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import yong.boardproject.domain.Article;

@RepositoryRestResource // Spring data resource를 사용
public interface ArticleRepository extends JpaRepository<Article, Long> {
}

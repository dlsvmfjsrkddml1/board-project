package yong.boardproject.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import yong.boardproject.domain.Article;
import yong.boardproject.domain.QArticle;

@RepositoryRestResource // Spring data resource를 사용
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {
    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        // 리스트에 올라오지 않은 속성들은 제외한다.
        bindings.excludeUnlistedProperties(true);
        // 제목 본문 해시테그 생성일시 생성자는 검색 기능으로 검색을 할 수 있게 한다.
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        // path와 value를 input 받아서 둘이 동일한 것인가? 검사하는 문장. // 대문자이든 소문자이든 무시하라.
        // likeIgnoreCase vs containsIgnoreCase 의 차이
        // likeIgnoreCase : like '${v}'
        // containsIgnoreCase : like '%${v}%'
        bindings.bind(root.title).first(/*(path, value)-> path.eq(value)*/ StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // 이 검색 방식은 그리 편리하게 하는 방법은 아니다.
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}

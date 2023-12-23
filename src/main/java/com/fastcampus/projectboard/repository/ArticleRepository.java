package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// JpaRepository란, 데이터베이스 조작을 위한 기본적인 CRUD(Create, Read, Update, Delete) 기능을 제공하는 인터페이스
// save(), findById(), findAll(), deleteById() 등의 CRUD 기능을 갖춘 메서드들이 포함되어있다.

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,             // Generic은 <T> = 일반 엔터티 || Article 안에 있는 모든 필드에 대한 기본 검색 기능 추가해줌
        QuerydslBinderCustomizer<QArticle>              // Generic은 <Entity Path> || 부분검색 기능
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true);           // listing 되지않은 속성들은 검색 불가하도록
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);         // query -> like '%s{v}%' 부분검색 가능
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}

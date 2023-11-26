package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// JpaRepository란, 데이터베이스 조작을 위한 기본적인 CRUD(Create, Read, Update, Delete) 기능을 제공하는 인터페이스
// save(), findById(), findAll(), deleteById() 등의 CRUD 기능을 갖춘 메서드들이 포함되어있다.

@RepositoryRestResource
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}

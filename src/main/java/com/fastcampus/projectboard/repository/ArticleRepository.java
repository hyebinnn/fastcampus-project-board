package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository란, 데이터베이스 조작을 위한 기본적인 CRUD(Create, Read, Update, Delete) 기능을 제공하는 인터페이스
// save(), findById(), findAll(), deleteById() 등의 CRUD 기능을 갖춘 메서드들이 포함되어있다.
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
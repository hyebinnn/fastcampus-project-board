package com.fastcampus.projectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})

// JPA를 사용하여 entity로 변경
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne = 연관관계 맵핑 (댓글-게시글 매핑)
    // optional = false = 해당 매핑 관계가 반드시 존재해야한다. 즉, ArticleComment는 하나의 Article에 속해야 함.
    @Setter @ManyToOne(optional = false) private Article article;            // 게시글 [id]
    @Setter @Column(nullable = false, length = 500) private String content;             // 본문

    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt;            // 생성일시
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy;                   // 생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt;           // 수정일시
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;                  // 수정자

    protected ArticleComment() {}

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    public static ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;              // pattern variable
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

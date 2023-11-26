package com.fastcampus.projectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

// 모든 필드는 접근이 가능해야한다.
// @Table = Entity 클래스와 매핑되는 데이터베이스 테이블에 대한 설정 지정
@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
// JPA를 사용하여 entity로 변경
@Entity
public class Article extends AuditingFields{
    @Id                                                             // 엔터티 클래스의 primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)             // DB가 자동으로 ID 생성
    private Long id;

    /*
    내용을 변경할 수 있으므로 setter 설정
    null이 가능한가 -> true가 default 값 => @Column 이라 표기 가능하지만, 파라미터 없이 쓴다면 생략도 가능!
    @Column = 엔터티 클래스의 필드와 데이터베이스 테이블의 컬럼 매핑
     */

    @Setter @Column(nullable = false) private String title;                       // 제목
    @Setter @Column(nullable = false, length = 10000) private String content;     // 본문

    // @Column을 생략해도 @Column으로 감지
    @Setter private String hashtag;                     // 해시태그

    @ToString.Exclude                                  // ToString 끊어주기
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();




    // entity는 public 또는 protected로 아무 인자없는 생성자가 필요합니다.
    protected Article() {

    }

    // 생성자가 private으로 선언되어 있으므로 외부에서 직접 객체 생성 X → of 메서드를 통해 객체 생성 유도
    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }


    // equals와 hashCode 메서드를 통해 객체의 동등성 비교
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;              // pattern variable
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

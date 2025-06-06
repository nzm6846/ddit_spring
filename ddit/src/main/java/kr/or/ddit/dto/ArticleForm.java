package kr.or.ddit.dto;

import kr.or.ddit.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

/*
DB의 큰 목적 2가지
   데이터 중복 방지(P.K), 데이터 불일치 방지(F.K)-참조무결성
 */
//자바빈 클래스 : 자바빈 규약을 지키는 클래스
//              1) 프로퍼티 2) 기본생성자 3) getter/setter메소드
/*AllArgsConstructor :
public ArticleForm(Long id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
}

골뱅이ToString : toString()메서드를 사용하는 것과 같음
*/
@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    //제목을 받을 필드
    private String title;
    //내용을 받을 필드
    private String content;
    //기본 생성자
    public ArticleForm() {}

    //getter/setter메소드
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //DTO를 엔티티로 변환해주는 메서드
    //{id=2,title=개똥이개똥이,content=즐거워즐거워}
    public Article toEntity() {
        //null -> id로 수정(글 수정을 위함)
        Article article = new Article(this.id,this.title,this.content);
        return article;
    }
}

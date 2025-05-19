package kr.or.ddit.dto;


/*
* DBMS의 큰 목적 2가지
* 데이터 중복 방지(P,K), 데이터 불일치 방지(F,K)-참조무결성3
*
* */

//자바빈 클래스 : 자바빈 규약을 지키는 클래스
//              1) 프로퍼티 2) 기본생성자 3) getter/setter메소드/*AllArgsConstructor :
//public ArticleForm(Long id, String title, String content) {
//    this.id = id;
//    this.title = title;
//    this.content = content;
//}
//*/

import kr.or.ddit.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

//골뱅이 ToString : toString()메소드를 사용하는 것과 같다
@AllArgsConstructor
@ToString

public class ArticleForm {

    private Long id;
    //제목을 받을 필드
    private String title;
    //내용을 받을 필드
    private String content;
    //기본생성자
    public ArticleForm() {

    }

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


    //DTO를 엔터티로 변환해주는 메서드
    public Article toEntity() {
        Article article = new Article(this.id,this.title,this.content);
        return article;
    }
}

package kr.or.ddit.dto;


import kr.or.ddit.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

// 자바빈 클래스 : 자바빈 규약을 지키는 클래스
//              1) 프로퍼티 2) 기본생성자 3) getter/setter메소드
/* AllArgsConstructor : 대신해준다
    public ArticleForm(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    ToString : toString()메서드를 사용하는것과 같음

*/
@AllArgsConstructor
@ToString
@Data
public class ArticleForm {

    private Long id;
    private String title;
    private String content;

    // DTO를 엔티티로 변환해주는 메서드
    public Article toEntity() {
        // null -> id로 수정(글 수정을 위한)
        Article article = new Article(this.id, this.title, this.content);

        return article;
    }
}

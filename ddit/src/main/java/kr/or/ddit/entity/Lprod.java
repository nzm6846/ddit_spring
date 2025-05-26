package kr.or.ddit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Data
public class Lprod {
    // 3. lprodId : 엔티티의 대푯값 지정
    // 3. GeneratedValue : 자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long lprodId;
    //2. lprodGu 필드 선언, DB 테이블의 lprodGu 열과 연결됨
    @Column
    private String lprodGu;
    //2. lprodNm 필드 선언, DB 테이블의 lprodNm 열과 연결됨
    @Column
    private String lprodNm;

    //NoArgsConstructor
    //public Lprod() {}

    //AllArgsConstructor

    //toString()..
}

package kr.or.ddit.controller;

import kr.or.ddit.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class FirstControllerTest {

    // DI
    @Autowired
    ArticleRepository articleRepository;

    // index() 메서드가 테스트 코드임을 선언
    @Test
    void index() {
        //1. 예상 데이터

        //2. 실제 데이터

        //3. 비교 및 검증

    }
}
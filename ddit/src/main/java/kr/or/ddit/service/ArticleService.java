package kr.or.ddit.service;

import kr.or.ddit.entity.Article;

import java.util.ArrayList;
import java.util.List;

//스프링 프레임워크에서 자바빈으로 등록해줌

public interface ArticleService {
    
    // 글 목록
    public ArrayList<Article> findAll();

    //과일 목록
    List<String> getStringList();
    //과일 목록
    ArrayList<String> getStringArrayList();

    //상세 글
    public Article findById(Long id);
}

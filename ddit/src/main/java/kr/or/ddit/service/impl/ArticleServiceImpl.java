package kr.or.ddit.service.impl;

import kr.or.ddit.entity.Article;
import kr.or.ddit.repository.ArticleRepository;
import kr.or.ddit.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    // 부모 메서드 재정의
    @Override
    public ArrayList<Article> findAll() {
        return this.articleRepository.findAll();
    }

    // 과일 목록
    @Override
    public List<String> getStringList() {
        // 내부적으로는 ArrayList를 사용하지만,
        // 언제든지 다른 List 구현체 (예: LinkedList)로 변경 가능
        List<String> list = new ArrayList<String>();
        list.add("Apple");
        list.add("Banana");
        // return new LinkedList<>(list); // 이렇게 변경해도 호출하는 쪽은 영향 없음
        return list;
    }

    @Override
    public ArrayList<String> getStringArrayList() {
        // 내부적으로는 ArrayList를 사용하지만,
        // 언제든지 다른 List 구현체 (예: LinkedList)로 변경 가능
        ArrayList<String> list = new ArrayList<String>();
        list.add("Apple");
        list.add("Banana");
        // return new LinkedList<>(list); // 이렇게 변경해도 호출하는 쪽은 영향 없음
        return list;
    }

    @Override
    public Article findById(Long id) {
        return this.articleRepository.findById(id).orElse(null);
    }
}

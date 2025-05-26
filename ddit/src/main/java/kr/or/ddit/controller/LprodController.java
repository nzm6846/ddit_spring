package kr.or.ddit.controller;

import kr.or.ddit.entity.Lprod;
import kr.or.ddit.dto.LprodForm;
import kr.or.ddit.repository.LprodRepository;
import kr.or.ddit.service.LprodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@Slf4j
@Controller
public class LprodController {


    //DI(Dependency Injection)
    @Autowired
    LprodRepository lprodRepository;

    @Autowired
    LprodService lprodService;
    /*
    2-1. 폼을 띄울 Get방식 요청(/lprod/new) Mapping. newArticleForm()메소드.
        return "lprod/new"; //forwarding
     */
    @GetMapping("/lprod/new")
    public String newArticleForm(){
        //forwarding : mustache
        return "lprod/new";
    }

    /* submit버튼 이벤트
    요청URI : /lprod/create
    요청파라미터 : request{lprodId='1',lprodGu=P101,lprodNm=컴퓨터제품}
    요청방식 : post

    DTO 이외의 매개변수로 request객체 안에 있는 파라미터를 받아보자
    스프링프레임워크에서 '1'라는 숫자형 문자 파라미터값을 int 타입의 매개변수로
        자동형변환하여 받을 수 있음
     */
    @PostMapping("/lprod/create")
    public String createLprod(LprodForm form,
                              int lprodId, String lprodGu, String lprodNm,
                              Map<String,Object> map){
        log.info("createLprod->form : " + form);
        log.info("createLprod->lprodId : " + lprodId);
        log.info("createLprod->lprodGu : " + lprodGu);
        log.info("createLprod->lprodNm : " + lprodNm);
        log.info("createLprod->map : " + map);

        //1. DTO(form)를 엔티티(lprod)로 변환
        Lprod lprod = form.toEntity();
        log.info("createLprod->lprod : " + lprod);

        //2. 리파지터리로 엔티티를 DB에 저장
        Lprod saved = this.lprodRepository.save(lprod);
        log.info("createLprod->saved : " + saved);

        //redirect : 새로운 URL을 재요청
        return "redirect:/lprod/new";
    }

    /*
    요청URI : /lprod/createAjax
    요철파라미터 : JSON String{"lprodId":"1","lprodGu":"P101","lprodNm":"컴퓨터제품"}
    요청방식 : post
     */
    @ResponseBody
    @PostMapping("/lprod/createAjax")
    public String createLprodAjax(@RequestBody LprodForm form){
        log.info("createLprodAjax->form : " + form);

        //데이터
        return "success";
    }

    /*
    요청URI : /lprod/1
    요청파라미터 :
    요청방식 : get
     */
    @GetMapping("/lprod/{id}")
    public String show(@PathVariable(value="id") Long lprodId,
            Model model){
        //id를 잘 받았는지 확인하는 로그 찍기
        log.info("show->lprodId : " + lprodId);
        //1. id를 조회해 데이터 가져오기
        // findById()는 JPA의 CrudRepository가 제공하는 메서드로, 특정 엔티티의 id 값을 기준으로
        //  데이터를 찾아 Optional 타입으로 반환.
        //orElse(null) : id 값으로 데이터를 찾을 때 해당 id 값이 없으면 null을 반환.
        // 데이터를 조회한 결과, 값이 있으면 lprodEntity 변수에 값을 넣고 없으면
        //  null을 저장
        Lprod lprodEntity = this.lprodRepository.findById(lprodId).orElse(null);
        log.info("show->lprodEntity : " + lprodEntity);

        //2. 모델에 데이터 등록하기
        //lprod이라는 이름으로 value인 lprodEntity 객체 추가
        model.addAttribute("lprod",lprodEntity);
        //3. 뷰 페이지 반환하기
        // 뷰 페이지는 lprod라는 디렉터리 안에 show.mustache라는 파일이 있다는 의미
        return "lprod/show";
    }

    /* 상품분류 목록
    요청URI : /lprods
    요청파라미터 :
    요청방식 : get
     */
    @GetMapping("/lprods")
    public String lprods(Model model){
        //Controller -> LprodService -> LprodServiceImpl -> findAll() 메소드 -> mapper의 findAll() 호출
        ArrayList<Lprod> lprodEntityList = this.lprodService.findAll();
        log.info("lprods->lprodEntityList : " + lprodEntityList);

        model.addAttribute("lprodList",lprodEntityList);

        return "lprod/index";
    }
}








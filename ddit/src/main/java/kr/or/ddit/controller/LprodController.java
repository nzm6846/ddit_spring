package kr.or.ddit.controller;

import kr.or.ddit.dto.LprodForm;
import kr.or.ddit.entity.Lprod;
import kr.or.ddit.repository.LprodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j
@Controller
public class LprodController {

    @Autowired
    private LprodRepository lprodRepository;

    @GetMapping("/lprod/new")
    public String newArticleForm() {

        return "lprod/new";
    }
    
    /*
    DTO 이외의 매개변수로 request 객체 안에 있는 파라미터를 받아보자
     */
    @PostMapping("/lprod/create")
    public String createLprod(LprodForm form) {

        String lprodGu = form.getLprodGu();
        String lprodNm = form.getLprodNm();

        System.out.println("lprodGu : " + lprodGu);
        System.out.println("lprodNm : " + lprodNm);

        // 1. DTO를 엔티티로 변환
        Lprod lprod = form.toEntity();
        log.info("createLprod -> lprod : " + lprod);

        // 2. 리퍼지토리 엔티티를 DB에 저장
        Lprod saved = this.lprodRepository.save(lprod);
        log.info("createSaved -> saved : " + saved);

        return "redirect:/lprod";
    }

    /*
    요청URI : /lprod/createAjax
    요철파라미터 : JSON String{"lprodId":"1","lprodGu":"P101","lprodNm":"컴퓨터제품"}
    요청방식 : post
     */
    @ResponseBody
    @PostMapping("/lprod/createAjax")
    public String createLprodAjax(@RequestBody LprodForm form) {

        log.info("LprodForm -> form : " + form);

        // 데이터
        return "success";
    }

    /*
    요청URI : /lprod/createAjax
    요철파라미터 : JSON String{"lprodId":"1","lprodGu":"P101","lprodNm":"컴퓨터제품"}
    요청방식 : post
     */
    @GetMapping("/lprod/{lprodId}")
    public String show(@PathVariable(value="lprodId") Long lprodId, Model model) {

        //id를 잘 받았는지 확인하는 로그 찍기

        //1. id를 조회해 데이터 가져오기
        // findById()는 JPA의 CrudRepository가 제공하는 메서드로, 특정 엔티티의 id 값을 기준으로
        //  데이터를 찾아 Optional 타입으로 반환.
        //orElse(null) : id 값으로 데이터를 찾을 때 해당 id 값이 없으면 null을 반환.
        // 데이터를 조회한 결과, 값이 있으면 lprodEntity 변수에 값을 넣고 없으면
        //  null을 저장

        //2. 모델에 데이터 등록하기
        //lprod이라는 이름으로 value인 lprodEntity 객체 추가

        //3. 뷰 페이지 반환하기
        // 뷰 페이지는 lprod라는 디렉터리 안에 show라는 파일이 있다는 의미
        Lprod lpordEntity = this.lprodRepository.findById(lprodId).orElse(null);
        log.info("lpordEntity : " + lpordEntity);

        model.addAttribute("lprod", lpordEntity);

        return "lprod/show";
    }
    @GetMapping("/lprod")
    public String index(Model model) {

        ArrayList<Lprod> lprodList = this.lprodRepository.findAll();

        model.addAttribute("lprodList", lprodList);

        return "lprod/index";
    }

    @GetMapping("/lprod/{lprodId}/edit")
    public String edit(@PathVariable(value="lprodId") Long lprodId, Model model) {

        Lprod lprodEntity = this.lprodRepository.findById(lprodId).orElse(null);

        model.addAttribute("lprodEntity", lprodEntity);

        return "lprod/edit";

    }

    @PostMapping("/lprod/update")
    public String editForm(LprodForm form) {

        Lprod lprodEntity = form.toEntity();

        Lprod target = this.lprodRepository.findById(lprodEntity.getLprodId()).orElse(null);

        if(target != null) {
            this.lprodRepository.save(lprodEntity);
        }

        return "redirect:/lprod/" + lprodEntity.getLprodId();
    }

    @GetMapping("/lprod/{lprodId}/delete")
    public String delete(@PathVariable(value="lprodId") Long lprodId, RedirectAttributes rttr) {

        Lprod lprodEntity = this.lprodRepository.findById(lprodId).orElse(null);

        rttr.addFlashAttribute("msg", "삭제되었습니다.");

        if(lprodEntity != null) {
            this.lprodRepository.delete(lprodEntity);
        }

        return "redirect:/lprod";
    }


}

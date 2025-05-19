package kr.or.ddit.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
REST 컨트롤러 : JSON이나 텍스트 같은 데이터 반환
일반 컨트롤러 : 뷰 페이지(jsp, mustache, thymeleaf) 반환(forwarding)
 */
@RestController
public class FirstApiController {
    /*
    요청URI : /api/hello
    요청파라미터 :
    요청방식 : get
     */
    @GetMapping("/api/hello")
    public String hello(){
        return "hello world!";
    }
}

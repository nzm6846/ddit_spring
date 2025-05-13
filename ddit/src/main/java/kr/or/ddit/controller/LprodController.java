package kr.or.ddit.controller;

import kr.or.ddit.dto.LprodForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class LprodController {

    @GetMapping("/lprod/new")
    public String newLprodForm(){

        return "lprod/new";
    }


    @PostMapping("/lprod/create")
    public String createLprod(LprodForm form) {

        log.info("createLprod->form : "+form);

        return "redirect:/lprod/new";
    }
}

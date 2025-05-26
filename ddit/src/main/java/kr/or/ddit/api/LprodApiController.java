package kr.or.ddit.api;

import kr.or.ddit.dto.LprodForm;
import kr.or.ddit.entity.Lprod;
import kr.or.ddit.repository.LprodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//RestController : 데이터를 응답
@RequestMapping("/api")
@Slf4j
@RestController
public class LprodApiController {

    //DI(의존성 주입) / IoC(제어의 역전)
    @Autowired
    LprodRepository lprodRepository;

    //비동기 목록 출력
    /*
    요청URI : /api/lprods
    요청파라미터 :
    요청방식 : post
    */
    //RestController에 의해서 ResponseBody 애너테이션 생략
    @PostMapping("/lprods")
    public List<LprodForm> lprods(){
        List<Lprod> lprodList = this.lprodRepository.findAll();
        /*
        lprods->lprodList : [Lprod(lprodId=1, lprodGu=P101, lprodNm=컴퓨터제품),
         Lprod(lprodId=2, lprodGu=P102, lprodNm=전자제품),
         Lprod(lprodId=3, lprodGu=P201, lprodNm=여성 캐주얼 의류)]
         */
        log.info("lprods->lprodList : " + lprodList);
        //데이터
        return entityToDto(lprodList);
    }

    /*
    요청URI : /api/lprod/1
    요청파라미터 : lprodId
    요청방식 : get
     */
    @GetMapping("/lprod/{lprodId}")
    public Lprod show(@PathVariable(value="lprodId") Long lprodId){
        log.info("show->lprodId : " + lprodId);
        //SELECT LPROD_ID, LPROD_GU, LPROD_NM FROM LPROD WHERE LPROD_ID = 2;
        Lprod lprodEntity = this.lprodRepository.findById(lprodId).orElse(null);
        log.info("show->lprodEntity : " + lprodEntity);
        //데이터 응답(JSON String)
        return lprodEntity;
    }

    //entity를 dto로 변환
    public List<LprodForm> entityToDto(List<Lprod> lprodList){
        List<LprodForm> lprodFormList = new ArrayList<LprodForm>();

        for(Lprod lprod : lprodList){
            LprodForm dto = new LprodForm();
            dto.setLprodId(lprod.getLprodId());
            dto.setLprodGu(lprod.getLprodGu());
            dto.setLprodNm(lprod.getLprodNm());
            lprodFormList.add(dto);
        }
        return lprodFormList;
    }

    @PostMapping("/lprod/update")
    public Lprod update(@RequestBody LprodForm form){
        log.info("update -> form :"+form);
        //        1. DTO를 엔티티로 변환
        //DTO(form)를 엔티티(lprodEntity)로 변환
        Lprod lprodEntity = form.toEntity();
        log.info("update -> lprodEntity :"+lprodEntity);

        //        2. 엔티티를 DB에 저장
        //2-1. DB에서 기존 데이터 가져오기(검증)
        Lprod target = this.lprodRepository.findById(lprodEntity.getLprodId()).orElse(null);
        log.info("update -> target :"+target);

        //2-2. 기존 데이터 값을 갱신하기
        //엔티티를 DB에 저장(갱신)
        if(target!=null){//검증완료
            target = this.lprodRepository.save(lprodEntity);
        }
        //데이터 응답
        return target;
    }

    @GetMapping("/test")
    public List<LprodForm> test(){
        List<Lprod> lprodList = this.lprodRepository.findAll();
        /*
        lprods->lprodList : [Lprod(lprodId=1, lprodGu=P101, lprodNm=컴퓨터제품),
         Lprod(lprodId=2, lprodGu=P102, lprodNm=전자제품),
         Lprod(lprodId=3, lprodGu=P201, lprodNm=여성 캐주얼 의류)]
         */
        log.info("lprods->lprodList : " + lprodList);

        List<LprodForm> lprodList2 = new ArrayList<LprodForm>();
        LprodForm lprod1 = new LprodForm();
        lprod1.setLprodGu("P101");
        lprodList2.add(lprod1);

        //데이터
        return lprodList2;
    }

    @PostMapping("lprods/delete")
    public Lprod delete(@RequestBody LprodForm lprodForm){
        // 로그: 삭제 요청이 수신되었음을 기록합니다.
        log.info("delete => lprodForm : " + lprodForm);

        //1) 삭제할 대상 가져오기
        // lprodRepository를 사용하여 전달받은 lprodId로 Lprod 엔티티를 데이터베이스에서 조회합니다.
        // 만약 해당 id의 엔티티가 존재하지 않으면 null을 반환합니다.
        // 로그: 조회된 삭제 대상 엔티티 정보를 기록합니다.
        Long lprodId = lprodForm.getLprodId();
        log.info("delete => lprodId : " + lprodId);

        Lprod target = this.lprodRepository.findById(lprodId).orElse(null);
        log.info("delete => target : " + target);

        //2) 대상 엔티티 삭제하기
        //삭제할 대상이 있는지 확인
        // 조회된 엔티티(target)가 null이 아닌지, 즉 삭제할 대상이 존재하는지 확인합니다.
        if(target != null) {
            //delete() 메서드로 대상 삭제
            // lprodRepository의 delete() 메소드를 호출하여 데이터베이스에서 해당 엔티티를 삭제합니다.
            this.lprodRepository.delete(target);
        }
        return target;
    }
}













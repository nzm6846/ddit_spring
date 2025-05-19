package kr.or.ddit.dto;

import kr.or.ddit.entity.Lprod;
import lombok.Data;

@Data
public class LprodForm {
    private Long lprodId;
    private String lprodGu;
    private String lprodNm;


    public Lprod toEntity() {
        Lprod lprod = new Lprod(this.lprodId, this.lprodGu, this.lprodNm);
        return lprod;
    }
}

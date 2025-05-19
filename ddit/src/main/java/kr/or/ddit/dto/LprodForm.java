package kr.or.ddit.dto;

import kr.or.ddit.entity.Lprod;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class LprodForm {
    //프로퍼티
    private int lprodId;
    private String lprodGu;
    private String lprodNm;

    //기본생성자 NoArgsConstructor
    //public LprodForm() {}

    public int getLprodId() {
        return lprodId;
    }

    public void setLprodId(int lprodId) {
        this.lprodId = lprodId;
    }

    public String getLprodGu() {
        return lprodGu;
    }

    public void setLprodGu(String lprodGu) {
        this.lprodGu = lprodGu;
    }

    public String getLprodNm() {
        return lprodNm;
    }

    public void setLprodNm(String lprodNm) {
        this.lprodNm = lprodNm;
    }

    public Lprod toEntity() {
        Lprod lprod = new Lprod(null,this.lprodGu,this.lprodNm);
        return lprod;
    }

    //toString()
}

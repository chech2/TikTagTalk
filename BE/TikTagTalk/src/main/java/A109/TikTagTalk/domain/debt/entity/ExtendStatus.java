package A109.TikTagTalk.domain.debt.entity;

import lombok.Getter;

public enum ExtendStatus {

    REQUESTING("승인 대기"), APPROVED("승인"), DENIED ("거절");

    @Getter
    private String status;
    ExtendStatus (String status){
        this.status = status;
    }

}
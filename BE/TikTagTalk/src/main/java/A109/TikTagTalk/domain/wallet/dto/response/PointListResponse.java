package A109.TikTagTalk.domain.wallet.dto.response;

import A109.TikTagTalk.domain.wallet.entity.PointHistory;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PointListResponse {
    private LocalDateTime pointTime;
    private Integer balancePoint;
    private int point;
    private String content;

    public PointListResponse(PointHistory entity){
        this.pointTime = entity.getPointTime();
        this.balancePoint = entity.getBalancePoint();
        this.point = entity.getPoint();
        this.content = entity.getContent();
    }
}

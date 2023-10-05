package A109.TikTagTalk.domain.wallet.dto.response;

import A109.TikTagTalk.domain.wallet.entity.CoinHistory;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoinListResponse {
    private LocalDateTime coinTime;
    private Integer balancePoint;
    private int coin;
    private String content;
    public CoinListResponse(CoinHistory entity) {
        this.coinTime = entity.getCoinTime();
        this.balancePoint = entity.getBalanceCoin();
        this.coin = entity.getCoin();
        this.content = entity.getContent();
    }
}

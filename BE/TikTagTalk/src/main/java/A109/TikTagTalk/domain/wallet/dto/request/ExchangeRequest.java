package A109.TikTagTalk.domain.wallet.dto.request;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.wallet.entity.ExchangeHistory;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExchangeRequest {

    private double exchangeRate;
    private LocalDateTime exchangeTime;
    private int point;
    private int coin;
    private Member member;

    public ExchangeHistory toEntity() {
        return ExchangeHistory.builder()
                .exchangeRate(exchangeRate)
                .pointTime(exchangeTime)
                .point(point)
                .coin(coin)
                .member(member)
                .build();
    }
}

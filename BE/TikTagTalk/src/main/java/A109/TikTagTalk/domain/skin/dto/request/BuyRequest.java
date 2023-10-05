package A109.TikTagTalk.domain.skin.dto.request;

import A109.TikTagTalk.domain.skin.entity.Skin;
import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.wallet.entity.CoinHistory;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BuyRequest {
    private Skin skin;
    private Member member;
    private Timestamp buytime;

    //구매 후 코인내역에 써있어야됨
    public CoinHistory toEntity(){
        return CoinHistory.builder().coinTime(buytime.toLocalDateTime()).member(member).content(skin.getItem().getName()).build();
    }


}

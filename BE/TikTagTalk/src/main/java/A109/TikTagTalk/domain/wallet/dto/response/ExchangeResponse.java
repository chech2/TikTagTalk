package A109.TikTagTalk.domain.wallet.dto.response;

import A109.TikTagTalk.domain.wallet.entity.CoinHistory;
import A109.TikTagTalk.domain.wallet.entity.ExchangeHistory;
import A109.TikTagTalk.domain.wallet.entity.PointHistory;

public class ExchangeResponse {
    private boolean exchanged;
    private Long id;

    public ExchangeResponse(ExchangeHistory entity){
        this.id = entity.getId();
    }
    public ExchangeResponse(CoinHistory entity){ this.id = entity.getId();    }
    public ExchangeResponse(PointHistory entity){
        this.id = entity.getId();
    }

}

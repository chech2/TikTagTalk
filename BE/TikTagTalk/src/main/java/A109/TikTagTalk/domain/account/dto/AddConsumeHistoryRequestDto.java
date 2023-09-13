package A109.TikTagTalk.domain.account.dto;

import A109.TikTagTalk.domain.tag.entity.Tag;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AddConsumeHistoryRequestDto {
    private Long amount;
    private TagDto tag;
    private String storeName;
    private String detail;
    private LocalDateTime consumeTime;
    private Long accountId;
    public AddConsumeHistoryRequestDto(Long accountId,Long amount,Tag tag,String storeName,String detail,LocalDateTime consumeTime){
        this.accountId=accountId;
        this.amount=amount;
        this.tag=new TagDto(tag.getId());
        this.storeName=storeName;
        this.detail=detail;
        this.consumeTime=consumeTime;
    }
    @Getter
    public class TagDto{
        private Long id; //프론트에선 해당 id에 대한 tag 이름을 보여주고 값을 넘길때는 tag의 id로.
        public TagDto(Long id){
            this.id=id;
        }
    }
}

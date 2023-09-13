package A109.TikTagTalk.domain.account.dto;

import A109.TikTagTalk.domain.tag.entity.Tag;
import lombok.Getter;

@Getter
public class CheckMemberTagResponseDto {
    private TagDto tag;
    private Long count;
    private Long amount;

    public CheckMemberTagResponseDto(Tag tag, Long count, Long amount){
        this.tag=new TagDto(tag.getId());
        this.count=count;
        this.amount=amount;
    }
    @Getter
    public static class TagDto{
        private Long id;
        public TagDto(Long id){
            this.id=id;
        }

    }
}

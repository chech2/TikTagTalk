package A109.TikTagTalk.domain.account.dto;

import A109.TikTagTalk.domain.tag.entity.Tag;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CheckAccountResponseDto {
    private Long totalAmount;

    private List<TagDto> tagList;
    public CheckAccountResponseDto(Long totalAmount,List<TagDto> tagList){
        this.totalAmount=totalAmount;
        this.tagList=tagList;
    }

    @Getter
    public static class TagDto{
        String name;
        Long amount;
        double percent;
        public TagDto(String name,Long amount,double percent){
            this.name=name;
            this.amount=amount;
            this.percent=percent;
        }
    }
}

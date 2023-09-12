package A109.TikTagTalk.domain.account.dto;

import A109.TikTagTalk.domain.tag.dto.TagDto;
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
}

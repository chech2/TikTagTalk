package A109.TikTagTalk.domain.tag.dto;

import lombok.Getter;

@Getter
public class TagDto {
    private String name;
    private Long amount;
    public TagDto(String name,Long amount){
        this.name=name;
        this.amount=amount;
    }
}

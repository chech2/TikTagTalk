package A109.TikTagTalk.domain.account.dto;

import A109.TikTagTalk.domain.account.entity.ConsumeHistory;
import A109.TikTagTalk.domain.tag.entity.Store;
import A109.TikTagTalk.domain.tag.entity.Tag;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AllConsumeHistoryResponseDto {
    private List<ConsumeHistoryDto> consumeHistoryList;
    public AllConsumeHistoryResponseDto(List<ConsumeHistory> consumeHistoryList){
        this.consumeHistoryList=consumeHistoryList.stream()
                .map(consumeHistory -> new ConsumeHistoryDto(consumeHistory.getAmount(),consumeHistory.getConsumeTime(),consumeHistory.getStore()))
                .collect(Collectors.toList());
    }
    @Getter
    public static class ConsumeHistoryDto {
        private Long amount;
        private LocalDateTime consumeTime;
        private StoreDto store;

        public ConsumeHistoryDto(Long amount, LocalDateTime consumeTime, Store store) {
            this.amount = amount;
            this.consumeTime = consumeTime;
            this.store=new StoreDto(store.getName(),store.getTag());
        }
    }
    @Getter
    public static class StoreDto{
        private String name;
        private TagDto tag;
        public StoreDto(String name, Tag tag){
            this.name=name;
            this.tag=new TagDto(tag.getName());
        }
    }

    @Getter
    public static class TagDto{
        private String name;
        public TagDto(String name){
            this.name=name;
        }
    }
}

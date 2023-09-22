package A109.TikTagTalk.domain.tagRoom.controller;

import A109.TikTagTalk.domain.tagRoom.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
@Slf4j
public class ItemController {
    private final ItemService itemService;

    @GetMapping() //s3에 있는 아이템 더미 데이터 삽입
    public void addItmes(){
        itemService.addItems();
    }

    

}

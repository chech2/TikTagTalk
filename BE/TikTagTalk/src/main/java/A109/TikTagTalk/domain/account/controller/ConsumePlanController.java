package A109.TikTagTalk.domain.account.controller;

import A109.TikTagTalk.domain.account.dto.request.InsertConsumePlanRequestDto;
import A109.TikTagTalk.domain.account.dto.response.ResponseDto;
import A109.TikTagTalk.domain.account.service.ConsumePlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
@Slf4j
public class ConsumePlanController {
    private final ConsumePlanService consumePlanService;

    @PostMapping("")
    public ResponseDto insertConsumePlan(@RequestBody InsertConsumePlanRequestDto requestDto){
        return consumePlanService.insertConsumePlan(requestDto);
    }
}

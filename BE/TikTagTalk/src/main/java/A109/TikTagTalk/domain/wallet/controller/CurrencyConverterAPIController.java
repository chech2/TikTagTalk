package A109.TikTagTalk.domain.wallet.controller;

import A109.TikTagTalk.domain.wallet.dto.ConvertInfoDto;
import A109.TikTagTalk.domain.wallet.service.CurrencyConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DecimalFormat;

@RestController
@RequestMapping("/api/exchange/buy")
@RequiredArgsConstructor
public class CurrencyConverterAPIController {
    private final CurrencyConverterService currencyConverter;
    private final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    @GetMapping("")
    public void test(){
        System.out.println("TESTETSTEST!!!!!");
    }
    //국가에 따라 환율을 가져오는 메소드
    @GetMapping("/exchange-rates")
    public ResponseEntity getExchangeRage(@RequestParam(name = "receiveCountry") String receiveCountry) {
        System.out.println("요청!!");
        Double exchangeRate = currencyConverter.getCurrencyRate(receiveCountry);
        return new ResponseEntity(format(exchangeRate), HttpStatus.OK);
    }

    //환율에 맞게 계산된 금액을 가져오는 메소드(ex, 3코인 설정시 1400*3 포인트)
    @PostMapping("/exchage-rates")
    public ResponseEntity getSendAmount(@Valid @RequestBody ConvertInfoDto convertInfoDto){

        Double currency = currencyConverter.getCurrencyRate(convertInfoDto.getReceiveCountry());
        Double sendAmount = (currency * convertInfoDto.getSendAmount());
        String formatSendAmount = format(sendAmount);

        return new ResponseEntity(formatSendAmount, HttpStatus.OK);
    }

    public String format(Number number){
        return decimalFormat.format(number);
    }
}

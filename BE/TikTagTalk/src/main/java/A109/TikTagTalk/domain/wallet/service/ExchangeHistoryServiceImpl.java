package A109.TikTagTalk.domain.wallet.service;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.wallet.dto.request.ExchangeRequest;
import A109.TikTagTalk.domain.wallet.dto.response.ExchangeResponse;
import A109.TikTagTalk.domain.wallet.entity.CoinHistory;
import A109.TikTagTalk.domain.wallet.entity.ExchangeHistory;
import A109.TikTagTalk.domain.wallet.entity.PointHistory;
import A109.TikTagTalk.domain.wallet.repository.CoinHistoryRepository;
import A109.TikTagTalk.domain.wallet.repository.ExchangeHistoryRepository;
import A109.TikTagTalk.domain.wallet.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeHistoryServiceImpl implements ExchangeHistoryService{
    private final ExchangeHistoryRepository exchangeHistoryRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final CoinHistoryRepository coinHistoryRepository;
    private final MemberRepository memberRepository;

    private static HttpURLConnection connection;
    private static BigDecimal defaultExchangeRate = BigDecimal.valueOf(1300);

    @Override
    public void test(ExchangeRequest request) {
        List<ExchangeHistory> list = exchangeHistoryRepository.findAll();
        return list;
    }

    @Override
    public ExchangeResponse exchangeCoin(LocalDateTime now, Long memberId, int coin, int point, Long exchangeRate) throws NullPointerException{
        //여기서 coin은 환전하려는 코인 갯수

        PointHistory pointHistory1 = pointHistoryRepository.findById(memberId).get();

        Member member = memberRepository.findById(memberId).get();

        Timestamp Tnow = Timestamp.valueOf(now);

        ExchangeRequest exchangeRequest = new ExchangeRequest();
        exchangeRequest.setExchangeTime(Tnow.toLocalDateTime());
        exchangeRequest.setCoin(coin);
        exchangeRequest.setPoint(point);
        exchangeRequest.setExchangeRate(exchangeRate);

        ExchangeHistory exchangeHistory = exchangeRequest.toEntity();

        exchangeHistoryRepository.save(exchangeHistory);

        CoinHistory coinHistory = new CoinHistory();
        coinHistory.setCoinTime(Tnow.toLocalDateTime());
        coinHistory.setCoin(0 + coin);
        coinHistory.setMember(member);

        List<CoinHistory> coinHistoryList = coinHistoryRepository.findAllByMember_Memberid(member.getId());
        Integer coins = coinHistoryList.get(coinHistoryList.size() - 1).getBalanceCoin();

        coinHistory.setBalanceCoin(coins);

        coinHistoryRepository.save(coinHistory);

        PointHistory pointHistory = new PointHistory();
        pointHistory = new PointHistory();
        pointHistory.setPointTime(Tnow.toLocalDateTime());
        pointHistory.setPoint(0 - point);
        pointHistory.setMember(member);

        List<PointHistory> pointHistoryList = pointHistoryRepository.findAllByMember_MemberId(member.getId());
        Integer points = pointHistoryList.get(pointHistoryList.size()-1).getBalancePoint();

        pointHistory.setBalancePoint(points);

        pointHistoryRepository.save(pointHistory);


        ExchangeResponse exchangeResponse = new ExchangeResponse(pointHistory);
        ExchangeResponse exchangeResponse1 = new ExchangeResponse(coinHistory);
        ExchangeResponse exchangeResponse2 = new ExchangeResponse(exchangeHistory);

        return null;
    }


    @Override
    public void exchangeAlgo(Long memberId, int amount) {
        PointHistory pointHistory = pointHistoryRepository.findById(memberId).orElse(null);
        CoinHistory coinHistory = coinHistoryRepository.findById(memberId).orElse(null);

        //멤버 전체가 보유하고 있는 포인트 개수 p -> member 테이블에 있는 포인트 SUM
        //서버에서 가지고있는 코인 개수 c


        //승아 아이디어 : 실제 환율비율로 맞추는 거
        //api key : KtEmgfK5laxBi7ihB5eq1zR3xiwZG1oO
        Optional<PointHistory> memberPoints = pointHistoryRepository.findById(memberId);
        int totalPoints = 0;

        for(PointHistory point : memberPoints){
            totalPoints += point.getBalancePoint();
        }

        Optional<CoinHistory> memberCoins = coinHistoryRepository.findById(memberId);
        int totalMemberCoins = 0;

        for(CoinHistory coin : memberCoins){
            totalMemberCoins += coin.getBalanceCoin();
        }

        int totalCoins = 5000;
        //포인트 개수가 증가할수록 환율 증가

    }

    public static BigDecimal getExchangeRate () {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        JSONParser parser = new JSONParser();

        String authKey = "KtEmgfK5laxBi7ihB5eq1zR3xiwZG1oO";
        String searchDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String dataType = "AP01";
        BigDecimal exchangeRate = null;

        try {
            // Request URL
            URL url = new URL("https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=" + authKey + "&searchdate=" + searchDate + "&data=" + dataType);
            connection = (HttpURLConnection) url.openConnection();

            // Request 초기 세팅
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            // API 호출
            // 실패했을 경우 Connection Close
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else { // 성공했을 경우 환율 정보 추출
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    JSON exchangeRateInfoList = (JSONArray) parser.parse(line);

                    // KRW -> USD에 대한 환율 정보 조회
                    for (Object o : exchangeRateInfoList) {
                        JSONObject exchangeRateInfo = (JSONObject) o;
                        if (exchangeRateInfo.get("cur_unit").equals("USD")) {

                            // 쉼표가 포함되어 String 형태로 들어오는 데이터를 Double로 파싱하기 위한 부분
                            NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
                            exchangeRate = new BigDecimal(format.parse(exchangeRateInfo.get("deal_bas_r").toString()).doubleValue());
                        }
                    }
                }
                reader.close();
            }
            System.out.println(responseContent.toString());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }

        if (exchangeRate == null) {
            exchangeRate = defaultExchangeRate;
        }

        return exchangeRate;
    }
}

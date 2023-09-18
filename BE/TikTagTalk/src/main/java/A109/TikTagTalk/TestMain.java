package A109.TikTagTalk;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestMain {
    public static void main(String[] args) {
        LocalDate nowTime= LocalDate.parse("2023-08-21");
        System.out.println(nowTime+"!!!!!!!!!!!!!!!");
        System.out.println("월 : "+nowTime.getMonth()+"!!!!!!!");
        System.out.println("월 : "+nowTime.getMonthValue()+"!!!!!!!");
        System.out.println("월 : "+nowTime.getDayOfMonth()+"!!!!!!!");
        System.out.println("마지막 날 : "+nowTime.lengthOfMonth()+"!!!");
    }
}

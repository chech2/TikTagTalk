package A109.TikTagTalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TikTagTalkApplication {

	public static void main(String[] args) {

		SpringApplication.run(TikTagTalkApplication.class, args);
	}

}

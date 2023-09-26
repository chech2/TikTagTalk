package A109.TikTagTalk.global;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SwaggerController {

    @GetMapping("/swagger")
    public String redirectSwaggerUI() {
        return "redirect:/swagger-ui/index.html";
    }
}

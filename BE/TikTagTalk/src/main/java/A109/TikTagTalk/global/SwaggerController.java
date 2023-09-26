package A109.TikTagTalk.global;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {

    @GetMapping("/swagger")
    public String redirectSwaggerUI() {
        return "redirect:/swagger-ui/index.html";
    }
}

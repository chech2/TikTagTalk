package A109.TikTagTalk.global.util;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.exception.custom.NoSuchUserException;
import A109.TikTagTalk.global.entity.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static Member getCurrentLoginMember() throws NoSuchUserException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal();
            CustomUserDetails customUserDetails = (CustomUserDetails) principal;
            return customUserDetails.getMember();
        }
        catch (Exception e) {
            throw new NoSuchUserException("인증되지 않은 사용자");
        }
    }
}

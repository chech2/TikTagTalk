package A109.TikTagTalk.domain.user.dto.response;

public class ExceptionResponseDto implements ResponseDto{

    private String message;

    public ExceptionResponseDto(String message) {
        this.message = message;
    }
}

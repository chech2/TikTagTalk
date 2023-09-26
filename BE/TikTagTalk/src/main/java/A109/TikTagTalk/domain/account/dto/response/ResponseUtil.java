package A109.TikTagTalk.domain.account.dto.response;

public class ResponseUtil {
    public static <T>ResponseDto<T> Success(String message){
        return new ResponseDto(ResponseStatus.SUCCESS,message);
    }
    public static <T>ResponseDto<T> Failure(String message){
        return new ResponseDto(ResponseStatus.FAILURE,message);
    }
    public static <T>ResponseDto<T> Error(String message){
        return new ResponseDto(ResponseStatus.ERROR,message);
    }
}

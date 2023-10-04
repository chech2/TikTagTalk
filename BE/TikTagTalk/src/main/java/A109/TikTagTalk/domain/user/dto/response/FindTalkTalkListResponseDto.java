package A109.TikTagTalk.domain.user.dto.response;

import A109.TikTagTalk.domain.user.entity.Member;
import A109.TikTagTalk.domain.user.entity.TalkTalkStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @Builder
public class FindTalkTalkListResponseDto {

    private Long id;
    private TalkTalkResponseStatus status;
    private Long otherId; // 상대방 id
    private String otherUserId; // 상대방 userId;
    private int otherAvatarType; // 상대방 avatarType

    public enum TalkTalkResponseStatus {

        REQUESTING, // 보낸 요청
        RECEIVED, // 받은 요청
        TALK_TALK // 톡톡 친구
    }

    public static FindTalkTalkListResponseDto toDTO(Long id, TalkTalkStatus status, boolean isSender, Member other) {
        if(status.equals(TalkTalkStatus.REQUESTING) && isSender) { // 보낸 요청 dto
            return FindTalkTalkListResponseDto.builder()
                    .id(id)
                    .status(TalkTalkResponseStatus.REQUESTING)
                    .otherId(other.getId())
                    .otherUserId(other.getUserId())
                    .otherAvatarType(other.getAvatarType())
                    .build();
        } else if (status.equals(TalkTalkStatus.REQUESTING) && !isSender) { // 받은 요청 dto
            return FindTalkTalkListResponseDto.builder()
                    .id(id)
                    .status(TalkTalkResponseStatus.RECEIVED)
                    .otherId(other.getId())
                    .otherUserId(other.getUserId())
                    .otherAvatarType(other.getAvatarType())
                    .build();
        } else {
            return FindTalkTalkListResponseDto.builder()
                    .id(id)
                    .status(TalkTalkResponseStatus.TALK_TALK)
                    .otherId(other.getId())
                    .otherUserId(other.getUserId())
                    .otherAvatarType(other.getAvatarType())
                    .build();
        }
    }
}
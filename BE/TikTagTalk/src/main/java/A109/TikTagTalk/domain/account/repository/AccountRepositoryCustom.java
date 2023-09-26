package A109.TikTagTalk.domain.account.repository;

import A109.TikTagTalk.domain.account.entity.Account;
import A109.TikTagTalk.domain.user.entity.Member;

public interface AccountRepositoryCustom {
    Account findAccountByMemberId(Member member);
}

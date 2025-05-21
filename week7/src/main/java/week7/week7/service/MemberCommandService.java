package week7.week7.service;

import week7.week7.domain.Member;
import week7.week7.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}

package week7.week7.web.converter;

import week7.week7.domain.Member;
import week7.week7.domain.enums.Gender;
import week7.week7.web.dto.MemberRequestDTO;
import week7.week7.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        // 함수가 반환하는 타입이 MemberResponseDTO.JoinResultDTO 임.
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build(); // 객체를 최종적으로 생성하는 메서드. 가독성 좋고 안전하게 만들 수 있게 됨.
        /* 이 코드랑 동일
        return MemberResponseDTO.JoinResultDTO dto =
            new MemberResponseDTO.JoinResultDTO(member.getID(), LocalDateTime.now());
        */
    }

    public static Member toMember(MemberRequestDTO.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }
}
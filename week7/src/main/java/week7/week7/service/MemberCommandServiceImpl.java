package week7.week7.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import week7.week7.apiPayload.code.status.ErrorStatus;
import week7.week7.apiPayload.exception.handler.FoodCategoryHandler;
import week7.week7.domain.FoodCategory;
import week7.week7.domain.Member;
import week7.week7.domain.MemberPrefer;
import week7.week7.repository.FoodCategoryRepository;
import week7.week7.repository.MemberRepository;
import week7.week7.web.converter.MemberConverter;
import week7.week7.web.converter.MemberPreferConverter;
import week7.week7.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}

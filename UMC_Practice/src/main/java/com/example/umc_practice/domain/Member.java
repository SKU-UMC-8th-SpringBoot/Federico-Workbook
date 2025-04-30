package com.example.umc_practice.domain;

import com.example.umc_practice.domain.common.BaseEntity;
import com.example.umc_practice.domain.enums.Gender;
import com.example.umc_practice.domain.enums.MemberStatus;
import com.example.umc_practice.domain.enums.Role;
import com.example.umc_practice.domain.enums.SocialType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 회원 PK

    private String name; // 이름

    @Column(name = "social_id", length = 100)
    private String socialId; // 소셜 아이디

    @Enumerated(EnumType.STRING)
    private SocialType socialType; // 소셜 제공자

    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별: 남, 여, 선택안함

    @Column(length = 100)
    private String address; // 주소

    private LocalDate birthDate; // 생년월일

    @Enumerated(EnumType.STRING)
    private Role role; // 역할: USER, ADMIN, OWNER

    @Enumerated(EnumType.STRING)
    private MemberStatus status; // 회원 상태

    @Column(columnDefinition = "DATETIME(6)")
    private LocalDateTime inactiveDate; // 비활성 날짜

    @Column(length = 20)
    private String password; // 회원 비밀번호

    @Column(name = "phone_number", length = 20)
    private String phoneNumber; // 회원 전화번호

    @Column(length = 20)
    private String nickname; // 닉네임

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region; // 외래키로 매핑

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
    // orphanRemoval = true .. 회원 삭제 시 review 도 삭제.

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberPointLog> pointLogs = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorites = new ArrayList<>();
}

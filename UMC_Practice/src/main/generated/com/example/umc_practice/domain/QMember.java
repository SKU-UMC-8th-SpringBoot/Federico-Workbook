package com.example.umc_practice.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 151166006L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final com.example.umc_practice.domain.common.QBaseEntity _super = new com.example.umc_practice.domain.common.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ListPath<Favorite, QFavorite> favorites = this.<Favorite, QFavorite>createList("favorites", Favorite.class, QFavorite.class, PathInits.DIRECT2);

    public final EnumPath<com.example.umc_practice.domain.enums.Gender> gender = createEnum("gender", com.example.umc_practice.domain.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> inactiveDate = createDateTime("inactiveDate", java.time.LocalDateTime.class);

    public final ListPath<MemberMission, QMemberMission> memberMissions = this.<MemberMission, QMemberMission>createList("memberMissions", MemberMission.class, QMemberMission.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final ListPath<MemberPointLog, QMemberPointLog> pointLogs = this.<MemberPointLog, QMemberPointLog>createList("pointLogs", MemberPointLog.class, QMemberPointLog.class, PathInits.DIRECT2);

    public final QRegion region;

    public final ListPath<Review, QReview> reviews = this.<Review, QReview>createList("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public final EnumPath<com.example.umc_practice.domain.enums.Role> role = createEnum("role", com.example.umc_practice.domain.enums.Role.class);

    public final StringPath socialId = createString("socialId");

    public final EnumPath<com.example.umc_practice.domain.enums.SocialType> socialType = createEnum("socialType", com.example.umc_practice.domain.enums.SocialType.class);

    public final EnumPath<com.example.umc_practice.domain.enums.MemberStatus> status = createEnum("status", com.example.umc_practice.domain.enums.MemberStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.region = inits.isInitialized("region") ? new QRegion(forProperty("region"), inits.get("region")) : null;
    }

}


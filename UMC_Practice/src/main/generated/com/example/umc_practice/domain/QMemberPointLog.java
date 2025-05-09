package com.example.umc_practice.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberPointLog is a Querydsl query type for MemberPointLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberPointLog extends EntityPathBase<MemberPointLog> {

    private static final long serialVersionUID = 12097002L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberPointLog memberPointLog = new QMemberPointLog("memberPointLog");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final QMission mission;

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final QRestaurant restaurant;

    public final DateTimePath<java.time.LocalDateTime> visitedAt = createDateTime("visitedAt", java.time.LocalDateTime.class);

    public QMemberPointLog(String variable) {
        this(MemberPointLog.class, forVariable(variable), INITS);
    }

    public QMemberPointLog(Path<? extends MemberPointLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberPointLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberPointLog(PathMetadata metadata, PathInits inits) {
        this(MemberPointLog.class, metadata, inits);
    }

    public QMemberPointLog(Class<? extends MemberPointLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.mission = inits.isInitialized("mission") ? new QMission(forProperty("mission"), inits.get("mission")) : null;
        this.restaurant = inits.isInitialized("restaurant") ? new QRestaurant(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}


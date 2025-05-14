package com.example.umc_practice.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberMission is a Querydsl query type for MemberMission
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberMission extends EntityPathBase<MemberMission> {

    private static final long serialVersionUID = -192121738L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberMission memberMission = new QMemberMission("memberMission");

    public final DateTimePath<java.time.LocalDateTime> completedAt = createDateTime("completedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final QMission mission;

    public final DateTimePath<java.time.LocalDateTime> receivedAt = createDateTime("receivedAt", java.time.LocalDateTime.class);

    public final EnumPath<MemberMission.MissionStatus> status = createEnum("status", MemberMission.MissionStatus.class);

    public QMemberMission(String variable) {
        this(MemberMission.class, forVariable(variable), INITS);
    }

    public QMemberMission(Path<? extends MemberMission> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberMission(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberMission(PathMetadata metadata, PathInits inits) {
        this(MemberMission.class, metadata, inits);
    }

    public QMemberMission(Class<? extends MemberMission> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.mission = inits.isInitialized("mission") ? new QMission(forProperty("mission"), inits.get("mission")) : null;
    }

}


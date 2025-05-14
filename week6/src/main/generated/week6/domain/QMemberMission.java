package week6.domain;

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

    private static final long serialVersionUID = -874524300L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberMission memberMission = new QMemberMission("memberMission");

    public final week6.domain.common.QBaseEntity _super = new week6.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final EnumPath<week6.domain.enums.MissionStatus> status = createEnum("status", week6.domain.enums.MissionStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

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
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}


package com.example.umc_practice.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMissionRestaurant is a Querydsl query type for MissionRestaurant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMissionRestaurant extends EntityPathBase<MissionRestaurant> {

    private static final long serialVersionUID = -846512435L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMissionRestaurant missionRestaurant = new QMissionRestaurant("missionRestaurant");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMission mission;

    public final QRestaurant restaurant;

    public QMissionRestaurant(String variable) {
        this(MissionRestaurant.class, forVariable(variable), INITS);
    }

    public QMissionRestaurant(Path<? extends MissionRestaurant> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMissionRestaurant(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMissionRestaurant(PathMetadata metadata, PathInits inits) {
        this(MissionRestaurant.class, metadata, inits);
    }

    public QMissionRestaurant(Class<? extends MissionRestaurant> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mission = inits.isInitialized("mission") ? new QMission(forProperty("mission"), inits.get("mission")) : null;
        this.restaurant = inits.isInitialized("restaurant") ? new QRestaurant(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}


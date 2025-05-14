package com.example.umc_practice.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestaurant is a Querydsl query type for Restaurant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestaurant extends EntityPathBase<Restaurant> {

    private static final long serialVersionUID = 935341785L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestaurant restaurant = new QRestaurant("restaurant");

    public final StringPath address = createString("address");

    public final TimePath<java.time.LocalTime> breakEnd = createTime("breakEnd", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> breakStart = createTime("breakStart", java.time.LocalTime.class);

    public final StringPath category = createString("category");

    public final StringPath closedDay = createString("closedDay");

    public final TimePath<java.time.LocalTime> closeTime = createTime("closeTime", java.time.LocalTime.class);

    public final ListPath<Favorite, QFavorite> favorites = this.<Favorite, QFavorite>createList("favorites", Favorite.class, QFavorite.class, PathInits.DIRECT2);

    public final ListPath<FoodRestaurant, QFoodRestaurant> foodRestaurants = this.<FoodRestaurant, QFoodRestaurant>createList("foodRestaurants", FoodRestaurant.class, QFoodRestaurant.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final ListPath<MissionRestaurant, QMissionRestaurant> missionRestaurants = this.<MissionRestaurant, QMissionRestaurant>createList("missionRestaurants", MissionRestaurant.class, QMissionRestaurant.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final TimePath<java.time.LocalTime> openTime = createTime("openTime", java.time.LocalTime.class);

    public final StringPath phoneNumber = createString("phoneNumber");

    public final QRegion region;

    public final ListPath<Review, QReview> reviews = this.<Review, QReview>createList("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public QRestaurant(String variable) {
        this(Restaurant.class, forVariable(variable), INITS);
    }

    public QRestaurant(Path<? extends Restaurant> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRestaurant(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRestaurant(PathMetadata metadata, PathInits inits) {
        this(Restaurant.class, metadata, inits);
    }

    public QRestaurant(Class<? extends Restaurant> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.region = inits.isInitialized("region") ? new QRegion(forProperty("region"), inits.get("region")) : null;
    }

}


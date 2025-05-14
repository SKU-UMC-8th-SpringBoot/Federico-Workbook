package com.example.umc_practice.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodRestaurant is a Querydsl query type for FoodRestaurant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodRestaurant extends EntityPathBase<FoodRestaurant> {

    private static final long serialVersionUID = -469136425L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodRestaurant foodRestaurant = new QFoodRestaurant("foodRestaurant");

    public final QFoodCategory foodCategory;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRestaurant restaurant;

    public QFoodRestaurant(String variable) {
        this(FoodRestaurant.class, forVariable(variable), INITS);
    }

    public QFoodRestaurant(Path<? extends FoodRestaurant> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodRestaurant(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodRestaurant(PathMetadata metadata, PathInits inits) {
        this(FoodRestaurant.class, metadata, inits);
    }

    public QFoodRestaurant(Class<? extends FoodRestaurant> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodCategory = inits.isInitialized("foodCategory") ? new QFoodCategory(forProperty("foodCategory")) : null;
        this.restaurant = inits.isInitialized("restaurant") ? new QRestaurant(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}


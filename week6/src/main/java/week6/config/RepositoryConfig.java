package week6.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import week6.repository.StoreRepository.StoreRepositoryCustom;
import week6.repository.StoreRepository.StoreRepositoryImpl;

@Configuration
public class RepositoryConfig {

    @Bean
    public StoreRepositoryCustom storeRepositoryCustom(JPAQueryFactory jpaQueryFactory) {
        return new StoreRepositoryImpl(jpaQueryFactory);
    }
}


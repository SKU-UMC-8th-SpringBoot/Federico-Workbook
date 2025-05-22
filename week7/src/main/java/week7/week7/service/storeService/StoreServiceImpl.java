package week7.week7.service.storeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import week7.week7.domain.Region;
import week7.week7.domain.Store;
import week7.week7.repository.RegionRepository;
import week7.week7.repository.StoreRepository;
import week7.week7.web.dto.StoreRequestDTO;


@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {


    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    public void addStore(Long regionId, StoreRequestDTO request) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없다."));

        Store store = Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .score(request.getScore())
                .region(region)
                .build();

        storeRepository.save(store);
    }
}

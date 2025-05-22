package week7.week7.service.storeService;

import week7.week7.web.dto.StoreRequestDTO;

public interface StoreService {

    void addStore(Long regionId, StoreRequestDTO request);
}

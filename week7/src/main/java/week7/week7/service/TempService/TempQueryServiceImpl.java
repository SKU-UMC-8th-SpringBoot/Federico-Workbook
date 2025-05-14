package week7.week7.service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import week7.week7.apiPayload.code.status.ErrorStatus;
import week7.week7.apiPayload.exception.handler.TempHandler;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService{

    @Override
    public void CheckFlag(Integer flag) {
        if (flag == 1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}

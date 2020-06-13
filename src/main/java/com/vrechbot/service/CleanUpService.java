package com.vrechbot.service;

import com.vrechbot.repository.PriceActionRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CleanUpService {
    private PriceActionRepository priceActionRepository;

    public CleanUpService(PriceActionRepository priceActionRepository) {
        this.priceActionRepository = priceActionRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteWithoutWhere() {
        priceActionRepository.deleteAll();
    }
}

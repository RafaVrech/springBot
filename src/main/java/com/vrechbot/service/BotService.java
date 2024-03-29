package com.vrechbot.service;

import com.vrechbot.domain.PriceAction;
import com.vrechbot.repository.PriceActionRepository;
import com.vrechbot.strategy.PairStrategy;
import com.vrechbot.strategy.PairStrategyFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class BotService {

    private final PriceActionRepository priceActionRepository;
    private final PairStrategyFactory pairStrategyFactory;

    public BotService(PriceActionRepository priceActionRepository, PairStrategyFactory pairStrategyFactory) {
        this.priceActionRepository = priceActionRepository;
        this.pairStrategyFactory = pairStrategyFactory;
    }

    @Async
    public void save(PriceAction priceAction) {
        priceActionRepository.save(priceAction);
    }

    @Scheduled(cron = "0 15 * * * ?")
    public void verifyTradeCall() {
        List<String> pairs = Arrays.asList("EURGBP", "EURUSD", "GBPUSP", "USDJPY");

        pairs.forEach(data ->
                pairStrategyFactory
                        .get(data)
                        .ifPresent(PairStrategy::checkTradeCalls)
        );
        System.out.println(LocalTime.now());
    }

}


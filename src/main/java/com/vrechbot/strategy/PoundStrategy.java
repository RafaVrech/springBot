package com.vrechbot.strategy;

import com.vrechbot.repository.PriceActionRepository;
import com.vrechbot.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class PoundStrategy extends PairStrategy {

    public PoundStrategy(PriceActionRepository priceActionRepository, MessageService messageService) {
        super(priceActionRepository, messageService);
    }

    @Override
    public String getType() {
        return "GBPUSD";
    }
}

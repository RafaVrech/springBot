package com.vrechbot.strategy;

import com.vrechbot.repository.PriceActionRepository;
import com.vrechbot.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class YenStrategy extends PairStrategy {

    public YenStrategy(PriceActionRepository priceActionRepository, MessageService messageService) {
        super(priceActionRepository, messageService);
    }

    @Override
    public String getType() {
        return "USDJPY";
    }
}

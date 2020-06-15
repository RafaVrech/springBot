package com.vrechbot.strategy;

import com.vrechbot.repository.PriceActionRepository;
import com.vrechbot.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class EuroStrategy extends PairStrategy {

    public EuroStrategy(PriceActionRepository priceActionRepository, MessageService messageService) {
        super(priceActionRepository, messageService);
    }

    @Override
    public String getType() {
        return "EURUSD";
    }

}

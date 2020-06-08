package com.vrechbot.service;

import com.vrechbot.domain.PriceAction;
import com.vrechbot.repository.PriceActionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BotService {

    private PriceActionRepository priceActionRepository;

    private MessageService messageService;

    public BotService(PriceActionRepository priceActionRepository, MessageService messageService) {
        this.priceActionRepository = priceActionRepository;
        this.messageService = messageService;
    }

    public void save(PriceAction priceAction) {
        priceActionRepository.save(priceAction);
//        messageService.sendMessage(priceAction.toString());

        priceActionRepository.findAllByIdDataGreaterThan(LocalDateTime.now().minusMinutes(2));
    }
}


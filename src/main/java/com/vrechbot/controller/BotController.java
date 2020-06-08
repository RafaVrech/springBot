package com.vrechbot.controller;

import com.vrechbot.domain.PriceAction;
import com.vrechbot.service.BotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/bot")
@RestController
public class BotController {

    private BotService botService;

    public BotController(BotService botService) {
        this.botService = botService;
    }

    @PostMapping
    public void save(@RequestBody PriceAction priceAction) {
        botService.save(priceAction);
    }
}

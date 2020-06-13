package com.vrechbot.strategy;

import org.springframework.stereotype.Service;

@Service
public class EuroStrategy extends PairStrategy {

    @Override
    public String getType() {
        return "EURUSD";
    }

}

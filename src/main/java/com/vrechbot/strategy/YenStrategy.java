package com.vrechbot.strategy;

import org.springframework.stereotype.Service;

@Service
public class YenStrategy extends PairStrategy {

    @Override
    public String getType() {
        return "USDJPY";
    }
}

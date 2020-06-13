package com.vrechbot.strategy;

import org.springframework.stereotype.Service;

@Service
public class PoundStrategy extends PairStrategy {

    @Override
    public String getType() {
        return "GBPUSD";
    }
}

package com.vrechbot.strategy;

import org.springframework.stereotype.Service;

@Service
public class EuroPoundStrategy extends PairStrategy {

    @Override
    public String getType() {
        return "EURGBP";
    }

}

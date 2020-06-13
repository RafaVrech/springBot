package com.vrechbot.strategy;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class PairStrategyFactory {

    private final List<PairStrategy> strategies;
    private final Map<String, PairStrategy> mapStrategies = new HashMap<>();

    public PairStrategyFactory(List<PairStrategy> strategies) {
        this.strategies = strategies;
    }

    @PostConstruct
    public void initMap() {
        for (PairStrategy strategy : strategies) {
            mapStrategies.put(strategy.getType(), strategy);
        }
    }

    public Optional<PairStrategy> get(String type) {
        return Optional.ofNullable(mapStrategies.get(type));
    }

}

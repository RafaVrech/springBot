package com.vrechbot.strategy;

import com.vrechbot.domain.PriceAction;
import com.vrechbot.repository.PriceActionRepository;
import com.vrechbot.service.MessageService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
public abstract class PairStrategy {

    private static final String BUY = "BUY ";
    private static final String SELL = "SELL ";
    private static final String USDJPY = "USDJPY";

    private final PriceActionRepository priceActionRepository;

    private final MessageService messageService;

    public PairStrategy(PriceActionRepository priceActionRepository, MessageService messageService) {
        this.priceActionRepository = priceActionRepository;
        this.messageService = messageService;
    }

    abstract String getType();

    public void checkTradeCalls() {
        List<PriceAction> buyActions = priceActionRepository.findBuyActionsByDataAndPair(LocalDateTime.now().minusMinutes(15), getType());
        List<PriceAction> sellActions = priceActionRepository.findSellActionsByDataAndPair(LocalDateTime.now().minusMinutes(15), getType());

        if (buyActions.size() >= 50 && buyActions.get(0).getPercentage() >= 90) {
            calculateAndSendMessage(buyActions, BUY);
        } else if (sellActions.size() >= 50 && buyActions.get(0).getPercentage() <= 10) {
            calculateAndSendMessage(sellActions, SELL);
        }
    }

    private void calculateAndSendMessage(List<PriceAction> priceAction, String orderType) {
        BigDecimal sum = priceAction.stream()
                .map(sell -> new BigDecimal(sell.getPreco())).reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(priceAction.size()), 5, RoundingMode.DOWN);

        BigDecimal takeProfit;
        BigDecimal stopLoss;

        if (BUY.equals(orderType)) {
            takeProfit = getType().equals(USDJPY) ? sum.add(BigDecimal.valueOf(0.1)) : sum.add(BigDecimal.valueOf(0.001)); //subtract 10 pips -> +- 10 bucks
            stopLoss = getType().equals(USDJPY) ? sum.subtract(BigDecimal.valueOf(0.1)) : sum.subtract(BigDecimal.valueOf(0.001));
        } else {
            takeProfit = getType().equals(USDJPY) ? sum.subtract(BigDecimal.valueOf(0.1)) : sum.subtract(BigDecimal.valueOf(0.001)); //subtract 10 pips -> +- 10 bucks
            stopLoss = getType().equals(USDJPY) ? sum.add(BigDecimal.valueOf(0.1)) : sum.add(BigDecimal.valueOf(0.001));
        }

        messageService.sendMessage("Wake Up!\n\n" + orderType + getType() +
                "\n\nActual price (media): " + sum +
                "\nTake Profit: " + takeProfit +
                "\nStop Loss: " + stopLoss);
    }
}

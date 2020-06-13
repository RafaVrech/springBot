package com.vrechbot.strategy;

import com.vrechbot.domain.PriceAction;
import com.vrechbot.repository.PriceActionRepository;
import com.vrechbot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
public abstract class PairStrategy {

    private static final String BUY = "BUY ";
    private static final String SELL = "SELL ";

    @Autowired
    private PriceActionRepository priceActionRepository;

    @Autowired
    private MessageService messageService;

    abstract String getType();

    public void checkTradeCalls() {
        List<PriceAction> buyActions = priceActionRepository.findBuyActionsByDataAndPair(LocalDateTime.now().minusMonths(2), getType());
        List<PriceAction> sellActions = priceActionRepository.findSellActionsByDataAndPair(LocalDateTime.now().minusMonths(2), getType());

        if (!buyActions.isEmpty() && buyActions.size() >= 6) {
            calculateAndSendMessage(buyActions, BUY);
        } else if (!sellActions.isEmpty() && sellActions.size() >= 6) {
            calculateAndSendMessage(sellActions, SELL);
        }
    }

    private void calculateAndSendMessage(List<PriceAction> priceAction, String orderType) {
        BigDecimal sum = priceAction.stream()
                .map(sell -> new BigDecimal(sell.getPreco())).reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(priceAction.size()), 4, RoundingMode.DOWN);

        BigDecimal takeProfit;
        BigDecimal stopLoss;

        if (BUY.equals(orderType)) {
            takeProfit = sum.add(BigDecimal.valueOf(0.002)); //subtract 20 pips -> +- 20 bucks
            stopLoss = sum.subtract(BigDecimal.valueOf(0.002));
        } else {
            takeProfit = sum.subtract(BigDecimal.valueOf(0.002)); //subtract 20 pips -> +- 20 bucks
            stopLoss = sum.add(BigDecimal.valueOf(0.002));
        }

        messageService.sendMessage("Wake Up!\n\n" + orderType + getType() +
                "\n\nActual price (media): " + sum +
                "\nTake Profit: " + takeProfit +
                "\nStop Loss: " + stopLoss);
    }
}

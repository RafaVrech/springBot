package com.vrechbot.service;

import com.vrechbot.client.Feign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Value(value = "${telegram.token}")
    private String botID;

    @Value(value = "${telegram.chat}")
    private String chat_id;

    @Value(value = "${telegram.parseMode}")
    private String parse_mode;

    private Feign feign;

    public MessageService(Feign feign) {
        this.feign = feign;
    }

    public void sendMessage(String mensagem) {
        feign.enviarMensagem(botID, chat_id, parse_mode, mensagem);
    }
}

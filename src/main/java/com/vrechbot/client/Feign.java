package com.vrechbot.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${telegram.url}", name = "telegram")
public interface Feign {

    @GetMapping(path = "bot{botID}/sendMessage")
    void enviarMensagem(@PathVariable String botID,
                        @RequestParam String chat_id,
                        @RequestParam String parse_mode,
                        @RequestParam String text);
}

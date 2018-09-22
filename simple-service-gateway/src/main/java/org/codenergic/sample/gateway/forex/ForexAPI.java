package org.codenergic.sample.gateway.forex;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "ForexService", fallback = ForexAPIFallback.class)
interface ForexAPI {

    @RequestMapping(method = RequestMethod.GET,
            value = "/convert/{from}/to/{to}")
    Forex convert(@PathVariable("from") String from,
                   @PathVariable("to") String to,
                   @RequestParam("amount")BigDecimal amount);

}

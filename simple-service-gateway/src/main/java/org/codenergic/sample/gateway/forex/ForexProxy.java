package org.codenergic.sample.gateway.forex;

import java.math.BigDecimal;

import org.codenergic.sample.gateway.forex.ForexProxy.ForexAPIFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ForexService", fallback = ForexAPIFallback.class)
interface ForexProxy {

    @RequestMapping(method = RequestMethod.GET,
            value = "/convert/{from}/to/{to}")
    Forex convert(@PathVariable("from") String from,
            @PathVariable("to") String to,
            @RequestParam("amount") BigDecimal amount);

    @Component
    class ForexAPIFallback implements ForexProxy {

        @Override
        public Forex convert(String from, String to, BigDecimal amount) {
            return new Forex(from, to, BigDecimal.ZERO, BigDecimal.ZERO);
        }
    }

}

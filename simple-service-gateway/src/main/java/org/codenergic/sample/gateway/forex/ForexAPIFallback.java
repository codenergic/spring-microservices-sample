package org.codenergic.sample.gateway.forex;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class ForexAPIFallback implements ForexAPI {

    @Override
    public Forex convert(String from, String to, BigDecimal amount) {
        return new Forex(from, to, BigDecimal.ZERO, BigDecimal.ZERO);
    }
}

package org.codenergic.sample.gateway.forex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forex {
    private String from;
    private String to;
    private BigDecimal rates;
    private BigDecimal amount;

    public Forex() {
    }

    public Forex(String from, String to, BigDecimal rates, BigDecimal amount) {
        this.from = from;
        this.to = to;
        this.rates = rates;
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getRates() {
        return rates;
    }

    public void setRates(BigDecimal rates) {
        this.rates = rates;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

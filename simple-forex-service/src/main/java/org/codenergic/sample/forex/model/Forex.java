package org.codenergic.sample.forex.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Forex
 */
@Validated
@javax.annotation.Generated(
        value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2018-10-01T09:46:42.687+07:00")

public class Forex {
    @JsonProperty("from")
    private String from = null;

    @JsonProperty("to")
    private String to = null;

    @JsonProperty("rates")
    private BigDecimal rates = null;

    @JsonProperty("amount")
    private BigDecimal amount = null;

    public Forex from(String from) {
        this.from = from;
        return this;
    }

    /**
     * Get from
     * 
     * @return from
     **/
    @ApiModelProperty(example = "IDR", required = true, value = "")
    @NotNull

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Forex to(String to) {
        this.to = to;
        return this;
    }

    /**
     * Get to
     * 
     * @return to
     **/
    @ApiModelProperty(example = "USD", required = true, value = "")
    @NotNull

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Forex rates(BigDecimal rates) {
        this.rates = rates;
        return this;
    }

    /**
     * Get rates
     * 
     * @return rates
     **/
    @ApiModelProperty(example = "14903.0", required = true, value = "")
    @NotNull

    @Valid

    public BigDecimal getRates() {
        return rates;
    }

    public void setRates(BigDecimal rates) {
        this.rates = rates;
    }

    public Forex amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Get amount
     * 
     * @return amount
     **/
    @ApiModelProperty(example = "149037.5", required = true, value = "")
    @NotNull

    @Valid

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Forex forex = (Forex) o;
        return Objects.equals(this.from, forex.from)
                && Objects.equals(this.to, forex.to)
                && Objects.equals(this.rates, forex.rates)
                && Objects.equals(this.amount, forex.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, rates, amount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Forex {\n");

        sb.append("    from: ").append(toIndentedString(from)).append("\n");
        sb.append("    to: ").append(toIndentedString(to)).append("\n");
        sb.append("    rates: ").append(toIndentedString(rates)).append("\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

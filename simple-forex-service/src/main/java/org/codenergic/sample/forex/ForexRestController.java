package org.codenergic.sample.forex;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class ForexRestController {

    @GetMapping("/convert/{from}/to/{to}")
    @ResponseBody
    public Forex convert(@PathVariable("from") String from,
                          @PathVariable("to") String to,
                          @RequestParam("amount") BigDecimal amount) {
        BigDecimal rates = new BigDecimal(1_500);
        return new Forex(from, to, rates, amount.multiply(rates));
    }

}

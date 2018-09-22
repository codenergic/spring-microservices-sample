package org.codenergic.sample.gateway.forex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class ForexController {

    private ForexAPI forexAPI;

    @Autowired
    public ForexController(ForexAPI forexAPI) {
        this.forexAPI = forexAPI;
    }

    @ResponseBody
    @RequestMapping("/forex/convert/{from}/to/{to}")
    public Forex convert(@PathVariable("from") String from,
                          @PathVariable("to") String to,
                          @RequestParam("amount") BigDecimal amount) {
        return forexAPI.convert(from, to, amount);
    }

}

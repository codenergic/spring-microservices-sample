package org.codenergic.sample.gateway.forex;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forex")
public class ForexController {

    private ForexAPI forexAPI;

    @Autowired
    public ForexController(ForexAPI forexAPI) {
        this.forexAPI = forexAPI;
    }

    @ResponseBody
    @GetMapping(value = "/convert/{from}/to/{to}")
    public Forex convert(@PathVariable("from") String from,
            @PathVariable("to") String to,
            @RequestParam("amount") BigDecimal amount) {
        return forexAPI.convert(from, to, amount);
    }

}

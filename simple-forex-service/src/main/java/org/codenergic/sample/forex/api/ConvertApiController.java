package org.codenergic.sample.forex.api;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.codenergic.sample.forex.model.Forex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;

@RestController
public class ConvertApiController implements ConvertApi {

    private static final Logger log = LoggerFactory
            .getLogger(ConvertApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ConvertApiController(ObjectMapper objectMapper,
            HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Forex> convertCurrency(
            @ApiParam(value = "source currency",
                    required = true) @PathVariable("from") String from,
            @ApiParam(value = "destination currency",
                    required = true) @PathVariable("to") String to,
            @ApiParam(value = "amount to convert") @Valid @RequestParam(
                    value = "amount", required = false) BigDecimal amount) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<>(objectMapper.readValue(
                        "{  \"amount\" : 149037.5,  \"rates\" : 14903.0,  \"from\" : \"IDR\",  \"to\" : \"USD\"}",
                        Forex.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error(
                        "Couldn't serialize response for content type application/json",
                        e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}

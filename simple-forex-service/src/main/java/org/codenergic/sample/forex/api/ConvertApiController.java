package org.codenergic.sample.forex.api;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.codenergic.sample.forex.model.Forex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

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

    public ResponseEntity<Forex> convertCurrency(String from, String to,
            BigDecimal amount, Authentication auth) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                log.info(auth.toString());
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

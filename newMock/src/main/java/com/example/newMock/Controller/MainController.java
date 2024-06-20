package com.example.newMock.Controller;

import com.example.newMock.Model.RequestDTO;
import com.example.newMock.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@RestController
public class MainController {
    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger log = LoggerFactory.getLogger(MainController.class);

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String postBalances(@RequestBody RequestDTO request) {
        try {
            if (!requestIsValid(request))
                throw new IllegalArgumentException("request fields such as clientId shall be filled in");
            BigDecimal limit = getLimitByRequest(request);
            ResponseDTO response = buildResponseByRequestData(request, limit);

            log.info("******* RequestDTO *******" + mapper.writeValueAsString(request));
            log.info("******* ResponseDTO *******" + mapper.writeValueAsString(response));

            return response.toString();
        } catch (Throwable ex) {
            log.error(ex.toString());
            return String.valueOf(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
        }
    }
    private boolean requestIsValid(RequestDTO request) {
        try {
            return !request.getClientId().isEmpty()
                    && !request.getRqUID().isEmpty()
                    && !request.getAccount().isEmpty();
        }
        catch (Exception e){
            throw new IllegalArgumentException("request fields such as UID, clientId or account shall exist");
        }
    }

    private BigDecimal getLimitByRequest(RequestDTO dto) {
        return switch (dto.getClientId().charAt(0)) {
            case '8' -> new BigDecimal(2000);
            case '9' -> new BigDecimal(1000);
            default -> new BigDecimal(10000);
        };
    }

    private ResponseDTO buildResponseByRequestData(RequestDTO request, BigDecimal limit) {
        return ResponseDTO.builder()
                .balance(setRandomBalanceLowerThanLimit(limit))
                .currency(setCurrencyByClientId(request))
                .rqUID(request.getRqUID())
                .clientId(request.getClientId())
                .account(request.getAccount())
                .maxLimit(String.valueOf(limit))
                .build();
    }

    private String setCurrencyByClientId(RequestDTO request) {
        return switch (request.getClientId().charAt(0)) {
            case '8' -> "US";
            case '9' -> "EU";
            default -> "RUB";
        };
    }

    private String setRandomBalanceLowerThanLimit(BigDecimal limit) {
        return makeLimitedString(generateLowerBalance(limit));
    }

    private String makeLimitedString(double limit) {
        return new DecimalFormat("#0.00").format(limit);
    }

    private double generateLowerBalance(BigDecimal limit) {
        return Math.random() * Double.parseDouble(String.valueOf(limit));
    }
}

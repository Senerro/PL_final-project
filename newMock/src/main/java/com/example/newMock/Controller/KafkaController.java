package com.example.newMock.Controller;

import com.example.newMock.Model.RequestDTO;
import com.example.newMock.Model.ResponseDTO;
import com.example.newMock.producers.KafkaProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;


import com.example.newMock.Model.MessageDTO;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@RestController
@RequiredArgsConstructor
public class KafkaController {
    private final Logger log = LoggerFactory.getLogger(KafkaController.class);
    private final KafkaProducer producer;
    private final ObjectMapper mapper = new ObjectMapper();
    @PostMapping(
            value = "/kafka/postInTopic/{topic}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String sendMessage(@RequestBody RequestDTO messageDTO, @PathVariable String topic){
        try {
            BigDecimal limit = getLimitByRequest(messageDTO);
            ResponseDTO response = buildResponseByRequestData(messageDTO, limit);
            producer.sendMessageInTopic(topic, response);
        }
        catch (Exception exception){
            return String.valueOf(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                                .body(exception.getMessage()));
        }
       return  String.valueOf(ResponseEntity.status(HttpStatus.OK)
                                            .body(messageDTO));
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

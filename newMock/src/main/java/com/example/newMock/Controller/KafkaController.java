package com.example.newMock.Controller;

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
    public String sendMessage(@RequestBody MessageDTO messageDTO, @PathVariable String topic){
        try {
            log.info(messageDTO + " is sending to topic " + topic);
            producer.sendMessageInTopic(topic, messageDTO);
            log.debug("Message was sent to topic " + topic);
        }
        catch (Exception exception){
            return String.valueOf(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                                .body(exception.getMessage()));
        }
       return  String.valueOf(ResponseEntity.status(HttpStatus.OK)
                                            .body(messageDTO));
    }
}

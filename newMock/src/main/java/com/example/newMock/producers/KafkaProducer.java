package com.example.newMock.producers;

import com.example.newMock.Model.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, MessageDTO> kafka;

    public void sendMessageInTopic(String topic, MessageDTO messageDTO){
        kafka.send(topic, messageDTO);
    }
}

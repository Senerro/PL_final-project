package com.example.newMock.producers;

import com.example.newMock.Model.MessageDTO;
import com.example.newMock.Model.RequestDTO;
import com.example.newMock.Model.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, ResponseDTO> kafka;

    public void sendMessageInTopic(String topic, ResponseDTO messageDTO){
        kafka.send(topic, messageDTO);
    }
}

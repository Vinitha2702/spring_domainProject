package demo.boot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import demo.boot.model.Doctor;

@Service
public class Producer {

    @Autowired
    KafkaTemplate<List<Doctor>, List<Doctor>> kafkaTemplate;
    public void sendMsgToTopic(List<Doctor> doctor) {
        kafkaTemplate.send("codeDecode_Topic", doctor);
        
    }

}
package com.example.Sweater.service;

import com.example.Sweater.models.Message;
import com.example.Sweater.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessageService {


    private final MessageRepository massageRepository;
    @Autowired
    public MessageService(MessageRepository massageRepository) {
        this.massageRepository = massageRepository;
    }


    public void created(String text, String tag){
         Message message = new Message(text, tag);
        log.info("сообщение созданно сообщение {}", message);
         massageRepository.save(message);
    }

    public List<Message> allMessage(){
        return massageRepository.findAll();
    }


    public List<Message> allTagMassage(String tag){
        return massageRepository.findByTag(tag);
    }
}

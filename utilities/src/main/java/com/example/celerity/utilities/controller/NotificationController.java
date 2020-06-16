package com.example.celerity.utilities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.celerity.dto.Message;

@Controller
public class NotificationController {

	@Autowired
	private SimpMessagingTemplate template;


    @MessageMapping("/send/message")
    public void sendMessage(Message message){
        System.out.println(message);
        this.template.convertAndSend("/message",  message);
    }

}

package com.UWCV2Service.controller;

import com.UWCV2Service.model.Message;
import com.UWCV2Service.model.User;
import com.UWCV2Service.repository.MessageRepository;
import lombok.Builder;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * ChatController
 */
@Controller
@Builder
public class ChatController {
  // private final SimpMessagingTemplate simpMessagingTemplate;
  private final MessageRepository messageRepository;

  @MessageMapping(value = {"/message"})
  @SendTo(value = {"/chatroom/public"})
  public Message receiveMessage(@Payload Message message) {
    return messageRepository.save(message);
  }

  @MessageMapping(value = "/joinroom")
  @SendTo(value = "/chatroom/public")
  public User joinRoom(@Payload User user) {
    return user;
  }
}

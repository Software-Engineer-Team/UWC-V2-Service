package com.UWCV2Service.controller;

import com.UWCV2Service.model.Message;
import com.UWCV2Service.model.Status;
import com.UWCV2Service.model.User;
import com.UWCV2Service.repository.MessageRepository;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.annotation.Order;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * ChatController
 */
@Controller
@Builder
@Slf4j
public class ChatController {
  private final SimpMessagingTemplate simpMessagingTemplate;
  private final MessageRepository messageRepository;
  private final List<UserJoin> usersJoinRoom;

  @MessageMapping(value = {"/message"})
  @SendTo(value = {"/chatroom/public"})
  public Message receiveMessage(@Payload Message message) {
    // return messageRepository.save(message);
    return message;
  }

  @MessageMapping(value = "/joinroom")
  @SendTo(value = "/chatroom/public/joinroom")
  public List<UserJoin> joinRoom(@Payload Message message) {
    log.info("joinRoom1: {}", message);
    usersJoinRoom.add(new UserJoin(message.getSender().getName(),
                                   message.getSender().getImgUrl(),
                                   message.getStatus()));
    log.info("joinRoom2: {}", usersJoinRoom);
    return usersJoinRoom;
  }

  @MessageMapping(value = "/leaveroom")
  @SendTo(value = "/chatroom/leaveroom")
  public List<UserJoin> leaveRoom(@Payload User user) {
    log.info("leaveroom: {}", user);
    usersJoinRoom.removeIf((userJoinRoom) -> userJoinRoom.getName().equals(user.getName()));
    return usersJoinRoom;
  }

  @Builder
  @Data
  static class UserJoin {
    private String name;
    private String imgUrl;
    private Status status;
  }
}

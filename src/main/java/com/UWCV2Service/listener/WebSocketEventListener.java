// package com.UWCV2Service.listener;
//
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.event.EventListener;
// import org.springframework.messaging.simp.SimpMessageSendingOperations;
// import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
// import org.springframework.stereotype.Component;
// import org.springframework.web.socket.messaging.SessionConnectedEvent;
// import org.springframework.web.socket.messaging.SessionDisconnectEvent;
//
// /**
//  * WebSocketEventListener
//  */
// @Component
// @Slf4j
// public class WebSocketEventListener {
//   @Autowired private SimpMessageSendingOperations messagingTemplate;
//
//   @EventListener
//   public void handleWebSocketConnectListener(SessionConnectedEvent event) {
//     log.info("Received a new web socket connection");
//   }
//
//   @EventListener
//   public void handleWebSocketDisconnectListener(SessionDisconnectEvent event)
//   {
//     log.info("event: {}", event);
//     log.info("user: {}", event.getUser());
//     log.info("message: {}", event.getMessage());
//     // StompHeaderAccessor headerAccessor =
//     //     StompHeaderAccessor.wrap(event.getMessage());
//
//     // String username =
//     //     (String)headerAccessor.getSessionAttributes().get("username");
//     // if (username != null) {
//     //   logger.info("User Disconnected : " + username);
//     //
//     //   ChatMessage chatMessage = new ChatMessage();
//     //   chatMessage.setType(ChatMessage.MessageType.LEAVE);
//     //   chatMessage.setSender(username);
//     //
//     //   messagingTemplate.convertAndSend("/topic/public", chatMessage);
//   }
// }

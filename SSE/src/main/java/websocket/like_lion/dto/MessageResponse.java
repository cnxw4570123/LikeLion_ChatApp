package websocket.like_lion.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import websocket.like_lion.domain.ChatMessage;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MessageResponse {
	private List<ChatMessage> chatMessages;
	private int totalCount;
}

package websocket.like_lion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import websocket.like_lion.domain.ChatMessage;

@AllArgsConstructor
@Getter
public class WriteMessageResponse {
	private ChatMessage chatMessage;

}

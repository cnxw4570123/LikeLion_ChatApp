package websocket.like_lion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WriteMessageRequest {
	private String authorName;
	private String content;
}

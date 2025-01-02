package websocket.like_lion.domain;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChatMessage {
	private Long id;
	private String authorName;
	private String content;
	private LocalDateTime createDate;

	public ChatMessage(String authorName, String content) {
		this.id = ChatMessageGenerator.genNextId();
		this.authorName = authorName;
		this.content = content;
		this.createDate = LocalDateTime.now();
	}

	class ChatMessageGenerator{
		private static AtomicLong id = new AtomicLong();

		public static long genNextId() {
			return id.incrementAndGet();
		}
	}

}

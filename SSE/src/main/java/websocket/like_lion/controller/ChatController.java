package websocket.like_lion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import websocket.like_lion.domain.ChatMessage;
import websocket.like_lion.domain.RsData;
import websocket.like_lion.dto.MessageRequest;
import websocket.like_lion.dto.MessageResponse;
import websocket.like_lion.dto.WriteMessageRequest;
import websocket.like_lion.dto.WriteMessageResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
	private List<ChatMessage> chatMessages = new ArrayList<>();
	private final SimpMessagingTemplate messagingTemplate;

	@PostMapping("/writeMessage")
	@ResponseBody
	public RsData<WriteMessageResponse> getMessage(@RequestBody WriteMessageRequest writeMessageRequest) {
		ChatMessage cm = new ChatMessage(writeMessageRequest.getAuthorName(),
			writeMessageRequest.getContent());
		chatMessages.add(cm);

		messagingTemplate.convertAndSend("/topic/chat/writeMessage", new WriteMessageResponse(cm));
		return new RsData<>("200", "메시지가 생성되었습니다.", new WriteMessageResponse(cm));
	}

	@GetMapping("/messages")
	@ResponseBody
	public RsData<MessageResponse> messages(MessageRequest messageRequest) {
		List<ChatMessage> messages = chatMessages;
		if (messageRequest.fromId() != null) {
			int idx = IntStream.range(0, messages.size())
				.filter(i -> chatMessages.get(i).getId() == messageRequest.fromId())
				.findFirst()
				.orElse(-1);

			if (idx != -1) {
				messages = messages.subList(idx + 1, messages.size());
			}
		}
		return new RsData<>("200", "메시지 가져오기 성공", new MessageResponse(messages, chatMessages.size()));
	}

	@GetMapping("/room")
	public String room () {
		return "/chat/room";
	}
}

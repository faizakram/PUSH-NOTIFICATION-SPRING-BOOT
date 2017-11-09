package ch.rasc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import ch.rasc.sse.eventbus.SseEvent;
import ch.rasc.sse.eventbus.SseEventBus;
@CrossOrigin
@Controller
public class SseController {

	private final SseEventBus eventBus;
		
	
	@Autowired
	private DataEmitterService dataEmitterService;
	
	public SseController(SseEventBus eventBus) {
		this.eventBus = eventBus;
	}

	//@GetMapping("/register/{id}")
	@RequestMapping(value = "/register/{id}",method = RequestMethod.GET)
	public SseEmitter register(@PathVariable("id") String id, @RequestHeader(value = "User-Agent") String userAgent) {
		//System.out.println("id ==== " + id);
		dataEmitterService.addListInfo(id);
		if (userAgent.contains("Edge/")) {
			// Use long polling instead of streaming
			// Create an emitter that closes the connection after sending each message
			// this is a workaround for the Microsoft Edge browser
			return this.eventBus.createSseEmitter(id, 180_000L, false, true, SseEvent.DEFAULT_EVENT);
		}
		//this.eventBus.setDataObjectConverters(dataObjectConverters);
		//return this.eventBus.createSseEmitter(id, events);
		return this.eventBus.createSseEmitter(id, SseEvent.DEFAULT_EVENT);
	}
	
	

}

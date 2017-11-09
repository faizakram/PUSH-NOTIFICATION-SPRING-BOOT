package ch.rasc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ch.rasc.sse.eventbus.SseEvent;
import ch.rasc.sse.eventbus.SseEvent.Builder;

@Service
public class DataEmitterService {

	private final ApplicationEventPublisher eventPublisher;
	// OR: private final ApplicationContext ctx;
	// this class implements the ApplicationEventPublisher interface
	private List<String> list = new ArrayList<String>(); 
	
	//private final static Random random = new Random();
	
	public DataEmitterService(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public void addListInfo(String id)
	{
		list.add(id);
	}
	

	
	
	@Scheduled(initialDelay = 2000, fixedRate = 5_000)
	public void sendData() {
		/*
		 * System.out.println("Send Data");
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < 5; i++) {
			sb.append(random.nextInt(31));
			sb.append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), "]");
*/	
		for(String str:list) {
		
		if(list.indexOf(str) == 0)
		{
		Builder builder1 = new SseEvent.Builder();
		Builder builder = builder1.from(SseEvent.ofData("Delhi"));
		builder.addClientId(str);
		this.eventPublisher.publishEvent(builder.build());
		}
		else
		{
			Builder builder1 = new SseEvent.Builder();
			Builder builder = builder1.from(SseEvent.ofData("Goa"));
			builder.addClientId(str);
			this.eventPublisher.publishEvent(builder.build());
		}
	}

	}

}

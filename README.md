# PUSH-NOTIFICATION

#When you using spring MVC using the version 4.2.4.RELEASE
  <code>
  <dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>4.2.4.RELEASE</version>
		</dependency>
  </code>

# The resion is SseEmiter Have parameterised constructor in 

#org.springframework.web.servlet.mvc.method.annotation.SseEmitter

#When calling 
<code>
	eventBus.createSseEmitter(id, SseEvent.DEFAULT_EVENT);
	// it calls SseEventBus -->
	public SseEmitter createSseEmitter(String clientId, Long timeout, boolean unsubscribe,
			boolean completeAfterMessage, String... events)
	SseEmitter emitter = new SseEmitter(timeout);
</code>

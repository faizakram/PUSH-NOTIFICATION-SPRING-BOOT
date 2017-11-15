# PUSH-NOTIFICATION Using SseEventBus and SseEmitter

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
<pre>
	eventBus.createSseEmitter(id, SseEvent.DEFAULT_EVENT);
	// it calls SseEventBus -->
	public SseEmitter createSseEmitter(String clientId, Long timeout, boolean unsubscribe,
			boolean completeAfterMessage, String... events)
	SseEmitter emitter = new SseEmitter(timeout);
</pre>

#Use in javascript

<pre>
var eventSource;
window.onbeforeunload = () => {
  if (eventSource) {
    eventSource.close();
  }
}
eventSource = new EventSource(`http://10.1.16.61:8080/SpringMVCHibernateWithSpringSecurity/register/${uuid}`);
eventSource.addEventListener('message', response => {
	for (let line of response.data.split('\n')) {
		console.log(line);
		handleResponse(JSON.parse(line));
	}
}, false);
</pre>

#Use in Angular JS
<pre>
const EventSource: any = window['EventSource'];
    const es = new EventSource('http://10.1.16.61:8080/SpringMVCHibernateWithSpringSecurity/register/${uuid}',
      { headers: { 'Content-Type': 'text/event-stream' } });
    es.onconnect = (e) => {
      console.log(e);
    }
    es.onmessage = evt => {
      console.log('Event source testing', evt);
      const data = JSON.parse(evt.data); // TODO handle parse error
    };
</pre>


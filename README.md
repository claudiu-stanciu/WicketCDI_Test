WicketCDI_Test
==============

Wicket + CDI + WebSockets use case test
Use case to test integration of Wicket + CDI Injection + WebSockets with the goal of achieving asynchronous beans.
IMyBeanAsync : @Stateful @RequestScoped @Asynchronous bean with 2 methods: 1 returns message to UI through WebSockets, the other doesn't.
+ Descriptions
  - MyPage contains the Wicket UI: 
    label1 - start message after button push
    label2 - stop message after bean stopped the workload			 
	- button no message - start thread wait, but don't push message back to UI after finish
	- button message - start thread wait, but push message back to UI after finish => triggers :
	[org.apache.wicket.protocol.ws.api.AbstractWebSocketProcessor] An error occurred during processing of a WebSocket message: 
	javax.enterprise.context.ContextNotActiveException: Conversation Context not active when method called on conversation Transient conversation

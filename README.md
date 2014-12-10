WicketCDI_Test
==============

Wicket + CDI + WebSockets use case test
Use case to test integration of Wicket + CDI Injection + WebSockets with the goal of achieving asynchronous beans.
+ Descriptions
  - MyPage contains the Wicket UI: 
    label1 - start message after button push
    label2 - stop message after bean stopped the workload
    		<button>CDI Async - start Asynchronous bean with Future<String> return to UI for stop event
  		<button>Executor - start bean with ExecutorService, the end event is a pushed message to a listener on WebSockets, 
  		which will update label2;			 
	- CDI Async: implement an Asynchronous bean, which will be injected with CDI in the main class MyPage. 
	- Executor: implement a bean, which will be injected with CDI in the main class MyPage. 
		The bean should implement successfully an ExecutorService which runs a Runnable class on a thread. 
		The Runnable class sleeps a thread for 5 secs, then pushed a message back to the UI through the listener;  
+ Flow :
	- CDI Async: starts the bean myBeanAsync, which contains a Future<String> implementation to sleep the thread for 5 secs 
		and log the flow. Returns a message when it stopped.
	- Executor: starts the bean myBeanExecutor, which starts runnable.run() a MyRunnable instance. runnable.run() sleeps
		the thread for 5 secs, logs the flow and pushed a message through the listener provided. 
		If myBeanExecutor starts an ExecutorService which runs runnable, then through listener.onMessage implementation ->
		connection.sendMessage() triggers a WELD-001303 No active contexts for scope type javax.enterprise.context.SessionScoped 
		when run in the context of an ExecutorService;

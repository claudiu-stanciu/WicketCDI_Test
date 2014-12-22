WicketCDI_Test
==============

Wicket + EJB Async + WebSockets use case test
Use case to test integration of Wicket + EJB Async + WebSockets with the goal of achieving asynchronous beans.
+ Descriptions
  - MyPage contains the Wicket UI: 
    label1 - start message after button push
    label2 - stop message after bean stopped the workload
    		<button>EJB Async - inject a Stateful Asynchronous bean into MyPage. The bean handles asynchronously the processing of a "long" job 
			and pushes a message back (when finished) to the UI through WebSockets.
+ Using
	- WildFly 8.1.0.final
	- JavaEE7
	- Wicket 7.0.0-M4
	- Wicket WebSockets
	- JBoss EJB 3.2

Testing the project
=======
The project packs to an EAR. You can deploy it to WildFly 8 = %JBOSS_HOME%\standalone\deployments\

deploy.bat handles the build with maven (mvn clean install) and copy the target(Inj3-ear.ear) to WildFly.

The webapp is accesed from http://localhost:8080/Inj3-web/




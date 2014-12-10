package org.amadeus.inj;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.ws.WebSocketSettings;
import org.apache.wicket.protocol.ws.api.IWebSocketConnection;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;
import org.apache.wicket.protocol.ws.api.registry.IWebSocketConnectionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkflowListener implements IWorkflowListener {
	private static final Logger LOG = LoggerFactory
			.getLogger(WorkflowListener.class);

	private WebSocketInfos wsinfos;

	public WorkflowListener(WebSocketInfos infos) {
		this.wsinfos = infos;
	}

	private IWebSocketConnection getConnection() {
		if (this.wsinfos != null) {
			Application application = Application.get(this.wsinfos
					.getApplicationName());
			WebSocketSettings settings = WebSocketSettings.Holder
					.get(application);
			IWebSocketConnectionRegistry registry = settings
					.getConnectionRegistry();

			return registry.getConnection(application, wsinfos.getSessionId(),
					wsinfos.getKey());
		} else {
			LOG.error("WebSocket client is unknown");
		}

		return null;
	}

	@Override
	public void onMessage(String message) {

		LOG.info("#onMessage");
		IWebSocketConnection connection = this.getConnection();

		if (connection != null && connection.isOpen()) {
			LOG.info("Websocket connection is opened");
			// Problem with sendMessage => WELD-001303 No active contexts for
			// scope type javax.enterprise.context.SessionScoped
			try{
				connection.sendMessage(new StatusMessage(String.valueOf(message)));
			}catch(Exception e){
				e.printStackTrace();
			}
		} else {
			LOG.info("Websocket connection is null or closed");
		}
	}

	public static class StatusMessage implements IWebSocketPushMessage {
		private final String status;

		public StatusMessage(String status) {
			this.status = String.valueOf(status);
		}

		public String getStatus() {
			return this.status;
		}
	}
}
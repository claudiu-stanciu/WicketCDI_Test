package org.amadeus.inj;

import org.apache.wicket.protocol.ws.api.registry.IKey;
import org.apache.wicket.util.io.IClusterable;

/**
 * WebSocket client infos (application, session, key)
 */
public class WebSocketInfos implements IClusterable
{
	private static final long serialVersionUID = 1L;

	private final String applicationName;
	private final String sessionId;
	private final IKey key;

	public WebSocketInfos(String applicationName, String sessionId, IKey key)
	{
		this.applicationName = applicationName;
		this.sessionId = sessionId;
		this.key = key;
	}

	public String getApplicationName()
	{
		return this.applicationName;
	}

	public String getSessionId()
	{
		return this.sessionId;
	}

	public IKey getKey()
	{
		return this.key;
	}
}
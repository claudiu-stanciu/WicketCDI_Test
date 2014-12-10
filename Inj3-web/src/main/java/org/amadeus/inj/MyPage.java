package org.amadeus.inj;

import java.util.concurrent.ExecutionException;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.amadeus.inj.WorkflowListener.StatusMessage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.ws.api.WebSocketBehavior;
import org.apache.wicket.protocol.ws.api.WebSocketRequestHandler;
import org.apache.wicket.protocol.ws.api.message.ConnectedMessage;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPage extends WebPage {

	private static final long serialVersionUID = 1L;

	@Inject
	private MyBeanAsync myBeanAsync;

	@Inject
	private MyBeanExecutor myBeanExecutor;
	
	@Inject
	private BeanManager bm;
	
	Label label1;
	Label label2;
	AjaxButton test1;
	AjaxButton test2;
	
	private WebSocketInfos wsinfos = null;
	Logger LOG = LoggerFactory.getLogger(MyPage.class);
	
	public MyPage(PageParameters parameters) {
		super(parameters);
		
		
		this.add(this.newWebSocketBehavior());
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();

		label1 = new Label("label1", Model.of("Started " + System.currentTimeMillis()));
		this.add(this.label1.setOutputMarkupId(true));
		
		label2 = new Label("label2", Model.of("Started " + System.currentTimeMillis()));
		this.add(this.label2.setOutputMarkupId(true));
		
		final Form<?> form = new Form<Void>("form");
		this.add(form);

		test1 = new AjaxButton("test1") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				label1.setDefaultModelObject("CDI Async bean start @ "+ new java.util.Date());
				target.add(label1);
				//TODO Rewrite CDI Async result call to not block UI thread 
				try {
					label2.setDefaultModelObject(myBeanAsync.start().get());
					target.add(label2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {					
					e.printStackTrace();
				}
			}
		};
		form.add(test1);
		
		test2 = new AjaxButton("test2") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				label1.setDefaultModelObject("CDI Exec Async bean start @ "+ new java.util.Date());
				target.add(label1);
				myBeanExecutor.start(newWorkflowListener());
			}
		};
		form.add(test2);
	}
	
	private final WebSocketBehavior newWebSocketBehavior() {
		return new WebSocketBehavior() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onConnect(ConnectedMessage message) {
				LOG.info("WebSocket client connected");
				wsinfos = this.newWebSocketInfos(message);
			}

			@Override
			protected void onPush(WebSocketRequestHandler handler,
					IWebSocketPushMessage message) {
				if (message instanceof StatusMessage) {
					String value = ((StatusMessage) message).getStatus();
					// See result of CDI Executor Asynchronous
					label2.setDefaultModelObject(value);
					handler.add(label2);
				}
			}

			@Override
			public void onException(Component component, RuntimeException e) {
				LOG.warn(e.getMessage(), e);
			}

			// Factories //

			private WebSocketInfos newWebSocketInfos(ConnectedMessage message) {
				return new WebSocketInfos(message.getApplication().getName(),
						message.getSessionId(), message.getKey());
			}
		};
	}

	private IWorkflowListener newWorkflowListener() {
		return new WorkflowListener(this.wsinfos);
	}

}

package domainevent.command.handler;

import javax.inject.Inject;

import com.google.gson.Gson;

import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;

public abstract class BaseEventHandler implements EventHandler {
    protected IEventPublisher jmsEventDispatcher;
    protected Gson gson;

    @Override
    public void handle(Object data) {
        this.jmsEventDispatcher.publish(this.getEventId(), data);
    }

    @Inject 
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public abstract void setJmsEventDispatcher(IEventPublisher jmsEventDispatcher);
    public abstract EventId getEventId(); 
}

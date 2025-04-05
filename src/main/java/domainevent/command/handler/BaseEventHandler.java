package domainevent.command.handler;

import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;

public abstract class BaseEventHandler implements EventHandler {
    protected IEventPublisher jmsEventDispatcher;

    @Override
    public void handle(Object event) {
        this.jmsEventDispatcher.publish(this.getEventId(), event);
    }

    public abstract void setJmsEventDispatcher(IEventPublisher jmsEventDispatcher);
    public abstract EventId getEventId(); 
}

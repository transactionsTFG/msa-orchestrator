package domainevent.command.handler;

import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;
import msa.commons.event.EventResponse;

public abstract class BaseEventHandler implements EventHandler {
    protected IEventPublisher jmsEventDispatcher;

    @Override
    public void handle(EventResponse eventResponse) {
        this.jmsEventDispatcher.publish(this.getEventId(), eventResponse);
    }

    public abstract void setJmsEventDispatcher(IEventPublisher jmsEventDispatcher);
    public abstract EventId getEventId(); 
}

package domainevent.command.typeuser;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.EventHandler;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import domainevent.publisher.typeuserqueue.JMSTypeUserPublisherQualifier;
import msa.commons.event.EventId;
import msa.commons.microservices.typeuser.qualifier.GetTypeQualifierV2;

@Stateless
@GetTypeQualifierV2
@Local(EventHandler.class)
public class GetTypeUserEvent extends BaseEventHandler {
    @Inject
    @Override
    public void setJmsEventDispatcher(@JMSTypeUserPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }
    @Override
    public EventId getEventId() {
        return EventId.VALIDATE_TYPE_USER;
    }
}
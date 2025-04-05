package domainevent.command.typeuser;

import javax.ejb.Stateless;
import javax.inject.Inject;

import domainevent.command.handler.EventHandler;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import domainevent.publisher.typeuserqueue.JMSTypeUserPublisherQualifier;
import msa.commons.event.EventId;
import msa.commons.microservices.typeuser.qualifier.GetTypeQualifierV2;

@Stateless
@GetTypeQualifierV2
public class GetTypeUserEvent implements EventHandler {

    private IEventPublisher jmsEventDispatcher;

    @Override
    public void handle(Object event) {
        this.jmsEventDispatcher.publish(EventId.GET_TYPE_USER, event);
    }

    @Inject
    public void setJmsEventDispatcher(@JMSTypeUserPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }
}
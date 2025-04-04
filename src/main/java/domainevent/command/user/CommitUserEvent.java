package domainevent.command.user;

import javax.ejb.Stateless;
import javax.inject.Inject;

import domainevent.command.handler.EventHandler;
import domainevent.publisher.jmseventpublisher.IJMSEventPublisher;
import domainevent.publisher.userqueue.JMSUserPublisherQualifier;
import msa.commons.event.EventId;
import msa.commons.microservices.user.qualifier.CommitUserQualifier;

@Stateless
@CommitUserQualifier
public class CommitUserEvent implements EventHandler {
    private IJMSEventPublisher jmsEventDispatcher;

    @Override
    public void handle(Object event) {
        this.jmsEventDispatcher.publish(EventId.CREATE_USER, event);
    }
    
    @Inject
    public void setJmsEventDispatcher(@JMSUserPublisherQualifier IJMSEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }
}

package business.command.typeuser;

import javax.ejb.Stateless;
import javax.inject.Inject;

import business.command.handler.EventHandler;
import business.eventdispatcher.eventdispatcher.IJMSEventDispatcher;
import business.eventdispatcher.qualifier.TypeUseJMSQualifier;
import msa.commons.event.EventId;
import msa.commons.microservices.typeuser.qualifier.GetTypeQualifierV2;

@Stateless
@GetTypeQualifierV2
public class GetTypeUserEvent implements EventHandler {

    private IJMSEventDispatcher jmsEventDispatcher;

    @Override
    public void handle(Object event) {
        this.jmsEventDispatcher.publish(EventId.GET_TYPE_USER, event);
    }

    @Inject
    public void setJmsEventDispatcher(@TypeUseJMSQualifier IJMSEventDispatcher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }
}
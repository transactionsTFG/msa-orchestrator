package business.command.user;

import javax.ejb.Stateless;
import javax.inject.Inject;

import business.command.handler.EventHandler;
import business.eventdispatcher.eventdispatcher.IJMSEventDispatcher;
import business.eventdispatcher.qualifier.UserJMSQualifier;
import msa.commons.event.EventId;
import msa.commons.microservices.user.qualifier.CommitUserQualifier;

@Stateless
@CommitUserQualifier
public class CommitUserEvent implements EventHandler {
    private IJMSEventDispatcher jmsEventDispatcher;

    @Override
    public void handle(Object event) {
        this.jmsEventDispatcher.publish(EventId.CREATE_USER, event);
    }
    
    @Inject
    public void setJmsEventDispatcher(@UserJMSQualifier IJMSEventDispatcher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }
}

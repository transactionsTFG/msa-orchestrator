package domainevent.command.user.createreservationairline;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.user.ValidateUserQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.EventHandler;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import domainevent.publisher.userqueue.JMSUserPublisherQualifier;
import msa.commons.event.EventId;

@Stateless
@ValidateUserQualifier
@Local(EventHandler.class)
public class ValidateUserReservationAirlineEvent extends BaseEventHandler {
    
    @Override
    public EventId getEventId() {
        return EventId.VALIDATE_USER;
    }

    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSUserPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }
;
}

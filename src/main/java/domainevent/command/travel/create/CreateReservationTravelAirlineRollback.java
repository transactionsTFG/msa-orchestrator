package domainevent.command.travel.create;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.CreateRollbackAirlineReservationEventQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.EventHandler;
import domainevent.publisher.arilineorchestratorqueue.JMSOrchestratorAirlinePublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;

@Stateless
@CreateRollbackAirlineReservationEventQualifier
@Local(EventHandler.class)
public class CreateReservationTravelAirlineRollback extends BaseEventHandler {

    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSOrchestratorAirlinePublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId getEventId() {
        return EventId.RESERVATION_AIRLINE_CREATE_RESERVATION_ROLLBACK_SAGA;
    }
    
}

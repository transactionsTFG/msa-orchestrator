package domainevent.command.airline;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.CreateAirlineReservationRollbackEventQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.EventHandler;
import domainevent.publisher.arilineorchestratorqueue.JMSOrchestratorAirlinePublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;

@Stateless
@CreateAirlineReservationRollbackEventQualifier
@Local(EventHandler.class)
public class CreateReservationAirlineRollback extends BaseEventHandler {
    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSOrchestratorAirlinePublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId getEventId() {
        return EventId.CREATE_RESERVATION_TRAVEL_ONLY_AIRLINE_ROLLBACK;
    }
}

package domainevent.command.travel.create;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.CreateRollbackHotelReservationEventQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.EventHandler;
import domainevent.publisher.hotelorchestratorqueue.JMSHotelOrchestratorPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;

@Stateless
@CreateRollbackHotelReservationEventQualifier
@Local(EventHandler.class)
public class CreateReservationTravelHotelRollback extends BaseEventHandler {

    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSHotelOrchestratorPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId getEventId() {
        return EventId.ROLLBACK_CREATE_HOTEL_BOOKING;
    }
}

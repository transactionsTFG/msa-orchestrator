package domainevent.command.travel.update;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.UpdateReservationHotelRollbackQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.EventHandler;
import domainevent.publisher.hotelorchestratorqueue.JMSHotelOrchestratorPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;

@Stateless
@UpdateReservationHotelRollbackQualifier
@Local(EventHandler.class)
public class UpdateReservationTravelHotelRollback extends BaseEventHandler {

    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSHotelOrchestratorPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId getEventId() {
        return EventId.ROLLBACK_UPDATE_HOTEL_BOOKING;
    }
}

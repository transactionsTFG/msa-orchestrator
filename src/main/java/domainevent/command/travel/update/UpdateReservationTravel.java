package domainevent.command.travel.update;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.UpdateReservationEventQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.EventHandler;
import domainevent.publisher.arilineorchestratorqueue.JMSOrchestratorAirlinePublisherQualifier;
import domainevent.publisher.hotelorchestratorqueue.JMSHotelOrchestratorPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import domainevent.publisher.travelqueue.JMSTravelPublisherQualifier;
import msa.commons.event.EventData;
import msa.commons.event.EventId;
import msa.commons.event.eventoperation.reservation.DeleteReservation;
import msa.commons.event.eventoperation.reservation.UpdateReservation;

@Stateless
@UpdateReservationEventQualifier
@Local(EventHandler.class)
public class UpdateReservationTravel extends BaseEventHandler {
    private IEventPublisher jmsEventDispatcherAirline;
    private IEventPublisher jmsEventDispatcherHotel;

    @Override
    public void handle(String json) {
        EventData data = this.gson.fromJson(json, EventData.class);
        if (UpdateReservation.UPDATE_RESERVATION_ONLY_AIRLINE_BEGIN.equals(data.getOperation())) 
            this.jmsEventDispatcherAirline.publish(EventId.CREATE_RESERVATION_TRAVEL, data);

        if (UpdateReservation.UPDATE_RESERVATION_ONLY_HOTEL_BEGIN.equals(data.getOperation())) 
            this.jmsEventDispatcherHotel.publish(EventId.CREATE_RESERVATION_TRAVEL, data);

        if (!UpdateReservation.UPDATE_RESERVATION_ONLY_AIRLINE_BEGIN.equals(data.getOperation()) && 
            !UpdateReservation.UPDATE_RESERVATION_ONLY_HOTEL_BEGIN.equals(data.getOperation()))
            this.jmsEventDispatcher.publish(EventId.CREATE_RESERVATION_TRAVEL, data);
    }

    @Inject
    public void setJmsEventDispatcherAirline(@JMSOrchestratorAirlinePublisherQualifier IEventPublisher jmsEventDispatcherAirline) {
        this.jmsEventDispatcherAirline = jmsEventDispatcherAirline;
    }

    @Inject
    public void setJmsEventDispatcherHotel(@JMSHotelOrchestratorPublisherQualifier IEventPublisher jmsEventDispatcherHotel) {
        this.jmsEventDispatcherHotel = jmsEventDispatcherHotel;
    }

    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSTravelPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId getEventId() {
        return EventId.UPDATE_RESERVATION_TRAVEL;
    }
}

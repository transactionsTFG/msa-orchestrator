package domainevent.command.airline;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.CreateAirlineReservationEventQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.EventHandler;
import domainevent.publisher.arilineorchestratorqueue.JMSOrchestratorAirlinePublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import domainevent.publisher.travelqueue.JMSTravelPublisherQualifier;
import msa.commons.event.EventData;
import msa.commons.event.EventId;
import msa.commons.event.eventoperation.reservation.ReservationAirline;


@Stateless
@CreateAirlineReservationEventQualifier
@Local(EventHandler.class)
public class CreateReservationAirlineTravel extends BaseEventHandler {
    private IEventPublisher jmsEventDispatcherAirline;

    @Override
    public void handle(String json) {
        EventData data = this.gson.fromJson(json, EventData.class);
        if (ReservationAirline.CREATE_RESERVATION_ONLY_AIRLINE_BEGIN.equals(data.getOperation())) 
            this.jmsEventDispatcherAirline.publish(EventId.CREATE_RESERVATION_TRAVEL, data);
        else 
            this.jmsEventDispatcher.publish(EventId.CREATE_RESERVATION_TRAVEL, data);
    }

    @Inject
    public void setJmsEventDispatcherAirline(@JMSOrchestratorAirlinePublisherQualifier IEventPublisher jmsEventDispatcherAirline) {
        this.jmsEventDispatcherAirline = jmsEventDispatcherAirline;
    }

    @Override
    @Inject
    public void setJmsEventDispatcher(@JMSTravelPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId getEventId() {
        return EventId.CREATE_RESERVATION_TRAVEL;
    }
    
}

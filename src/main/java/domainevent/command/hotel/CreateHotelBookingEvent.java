package domainevent.command.hotel;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.CreateHotelBookingEventQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.EventHandler;
import domainevent.publisher.hotel.JMSHotelBookingPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
@CreateHotelBookingEventQualifier
@Local(EventHandler.class)
public class CreateHotelBookingEvent extends BaseEventHandler {

    private static final Logger LOGGER = LogManager.getLogger(CreateHotelBookingEvent.class);

    @Inject
    @Override
    public void setJmsEventDispatcher(@JMSHotelBookingPublisherQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId getEventId() {
        return EventId.BEGIN_CREATE_HOTEL_BOOKING;
    }

    @Override
    public void handle(Object data) {
        LOGGER.info("METODO SIN IMPLEMENTAR");
    }

}

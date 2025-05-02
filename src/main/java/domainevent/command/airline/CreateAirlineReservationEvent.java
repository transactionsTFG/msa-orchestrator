package domainevent.command.airline;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business.qualifier.CreateAirlineReservationEventQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.EventHandler;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;

@Stateless
@CreateAirlineReservationEventQualifier
@Local(EventHandler.class)
public class CreateAirlineReservationEvent extends BaseEventHandler {

    private static final Logger LOGGER = LogManager.getLogger(CreateAirlineReservationEvent.class);

    @Override
    public void setJmsEventDispatcher(IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public EventId getEventId() {
        return EventId.RESERVATION_AIRLINE_CREATE_RESERVATION_BEGIN_SAGA;
    }

    @Override
    public void handle(Object data) {
        LOGGER.info("METODO SIN IMPLEMENTAR");
    }

}

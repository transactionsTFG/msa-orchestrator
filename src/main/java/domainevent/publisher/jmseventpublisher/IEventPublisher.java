package domainevent.publisher.jmseventpublisher;


import msa.commons.event.EventId;
import msa.commons.event.EventResponse;

public interface IEventPublisher {
    void publish(EventId eventId, EventResponse eventResponse);
}

package domainevent.publisher.jmseventpublisher;


import msa.commons.event.EventData;
import msa.commons.event.EventId;

public interface IEventPublisher {
    void publish(EventId eventId, EventData data);
}

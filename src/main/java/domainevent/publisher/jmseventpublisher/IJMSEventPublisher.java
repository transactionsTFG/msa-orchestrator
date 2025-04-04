package domainevent.publisher.jmseventpublisher;

import msa.commons.event.EventId;

public interface IJMSEventPublisher {
    void publish(EventId eventId, Object data);
}

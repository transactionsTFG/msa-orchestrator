package business.eventdispatcher.eventdispatcher;

import msa.commons.event.EventId;

public interface IJMSEventDispatcher {
    void publish(EventId eventId, Object data);
}

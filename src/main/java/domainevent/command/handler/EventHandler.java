package domainevent.command.handler;

import msa.commons.event.EventResponse;

public interface EventHandler {
    void handle(EventResponse eventResponse);
}

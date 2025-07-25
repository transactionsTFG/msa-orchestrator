package domainevent.consumer;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import domainevent.command.handler.EventHandler;
import domainevent.registry.EventHandlerRegistry;
import msa.commons.consts.JMSQueueNames;
import msa.commons.event.Event;

@MessageDriven(mappedName = JMSQueueNames.AGENCY_ORCHESTATOR_QUEUE)
public class DomainEventConsumerOrchestator implements MessageListener {
    private Gson gson;
    private EventHandlerRegistry eventHandlerRegistry;
    private static final Logger LOGGER = LogManager.getLogger(DomainEventConsumerOrchestator.class);

    @Override
    public void onMessage(Message msg) {
         try {
            if(msg instanceof TextMessage m) {
                Event event = this.gson.fromJson(m.getText(), Event.class);
                LOGGER.info("Recibido en Cola {}, Evento Id: {}, Mensaje: {}", JMSQueueNames.AGENCY_ORCHESTATOR_QUEUE, event.getEventId(), event.getValue());
                EventHandler commandHandler = this.eventHandlerRegistry.getHandler(event.getEventId());
                if(commandHandler != null)
                    commandHandler.handle(this.gson.toJson(event.getValue()));
            }
        } catch (Exception e) {
            LOGGER.error("Error al recibir el mensaje: {}", e.getMessage());
        }
    }

    @Inject
    public void setGson(Gson gson) { this.gson = gson; }
    @EJB
    public void setCommandHandlerRegistry(EventHandlerRegistry commandHandlerRegistry) { this.eventHandlerRegistry = commandHandlerRegistry; }
}
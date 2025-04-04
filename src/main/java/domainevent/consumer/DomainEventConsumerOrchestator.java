package domainevent.consumer;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.google.gson.Gson;

import domainevent.command.handler.EventHandler;
import domainevent.registry.EventHandlerRegistry;
import msa.commons.event.Event;

@MessageDriven(mappedName = "jms/orchestatorQueue")
public class DomainEventConsumerOrchestator implements MessageListener {
    private Gson gson;
    private EventHandlerRegistry eventHandlerRegistry;

    @Override
    public void onMessage(Message msg) {
         try {
            if(msg instanceof TextMessage m) {
                Event event = this.gson.fromJson(m.getText(), Event.class);
                EventHandler commandHandler = this.eventHandlerRegistry.getHandler(event.getEventId());
                if(commandHandler != null)
                    commandHandler.handle(event.getData());
            }
        } catch (Exception e) {
            System.out.println("Error al recibir el mensaje: " + e.getMessage());
        }
    }

    @Inject
    public void setGson(Gson gson) { this.gson = gson; }
    @EJB
    public void setCommandHandlerRegistry(EventHandlerRegistry commandHandlerRegistry) { this.eventHandlerRegistry = commandHandlerRegistry; }
}
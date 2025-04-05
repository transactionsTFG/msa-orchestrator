package domainevent.publisher.jmseventpublisher;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import msa.commons.consts.PropertiesConsumer;
import msa.commons.event.Event;
import msa.commons.event.EventId;
import msa.commons.event.EventResponse;

public abstract class BaseJMSEventPublisher implements IEventPublisher {
    private ConnectionFactory connectionFactory;
    private Gson gson;
    protected Queue queue;
    private static final Logger LOGGER = LogManager.getLogger(BaseJMSEventPublisher.class);

    @Override
    public void publish(EventId eventId, EventResponse eventResponse) {
        try (JMSContext jmsContext = connectionFactory.createContext()) {
            Event sendMsg = new Event(eventId, eventResponse);
            TextMessage txt = jmsContext.createTextMessage(this.gson.toJson(sendMsg));
            txt.setStringProperty(PropertiesConsumer.ORIGIN_QUEUE, this.getQueueName());
            LOGGER.info("Publicando en Cola {}, Evento Id: {}, Mensaje: {}", this.getQueueName(), eventId, eventResponse);
            jmsContext.createProducer().send(this.queue, txt);
        } catch (Exception e) {
            LOGGER.error("Error al publiar el mensaje: {}", e.getMessage());
        }
    }

    @Inject
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Inject
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public abstract void setQueueInject(Queue queueInject);
    public abstract String getQueueName();
}
package domainevent.publisher.userqueue;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import com.google.gson.Gson;

import domainevent.publisher.jmseventpublisher.IJMSEventPublisher;
import integration.producer.qualifiers.UserQueue;
import msa.commons.event.Event;
import msa.commons.event.EventId;

@Stateless
@JMSUserPublisherQualifier
public class JMSUserPublisher implements IJMSEventPublisher {
    private ConnectionFactory connectionFactory;
    private Queue userServiceQueue;
    private Gson gson;

    @Override
    public void publish(EventId eventId, Object data) {
        try(JMSContext jmsContext = connectionFactory.createContext()) {
            Event sendMsg = new Event(eventId, data);
            jmsContext.createProducer().send(this.userServiceQueue, this.gson.toJson(sendMsg));
        }
    }

    @Inject
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Inject
    public void setUserServiceQueue(@UserQueue Queue userServiceQueue) {
        this.userServiceQueue = userServiceQueue;
    }

    @Inject
    public void setGson(Gson gson) {
        this.gson = gson;
    }
    
}

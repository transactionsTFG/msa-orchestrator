package domainevent.publisher.typeuserqueue;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import com.google.gson.Gson;

import domainevent.publisher.jmseventpublisher.IJMSEventPublisher;
import integration.producer.qualifiers.TypeUserQueue;
import msa.commons.event.EventId;
import msa.commons.event.Event;

@Stateless
@JMSTypeUserPublisherQualifier
public class JMSTypeUserPublisher implements IJMSEventPublisher {
    private ConnectionFactory connectionFactory;
    private Queue typeUserServiceQueue;
    private Gson gson;

    @Override
    public void publish(EventId eventId, Object data){
        try(JMSContext jmsContext = connectionFactory.createContext()) {
            Event sendMsg = new Event(eventId, data);
            jmsContext.createProducer().send(this.typeUserServiceQueue, this.gson.toJson(sendMsg));
        }
    }

    @Inject
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
    @Inject
    public void setTypeUserServiceQueue(@TypeUserQueue Queue typeUserServiceQueue) {
        this.typeUserServiceQueue = typeUserServiceQueue;
    }
    @Inject
    public void setGson(Gson gson) {
        this.gson = gson;
    }
}

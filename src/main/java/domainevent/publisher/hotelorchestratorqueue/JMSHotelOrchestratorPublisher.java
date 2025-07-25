package domainevent.publisher.hotelorchestratorqueue;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Queue;

import domainevent.publisher.jmseventpublisher.BaseJMSEventAirlinePublisher;
import domainevent.publisher.jmseventpublisher.BaseJMSEventHotelPublisher;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import integration.producer.qualifiers.OrchestratorHotelQueue;
import msa.commons.consts.JMSQueueNames;

@Stateless
@JMSHotelOrchestratorPublisherQualifier
@Local(IEventPublisher.class)
public class JMSHotelOrchestratorPublisher extends BaseJMSEventHotelPublisher {

    @Override
    @Inject
    public void setQueueInject(@OrchestratorHotelQueue Queue queueInject) {
       this.queue = queueInject;
    }

    @Override
    public String getQueueName() {
        return JMSQueueNames.REMOTE_ORCHESTRATOR_HOTEL_QUEUE;
    }
    
}

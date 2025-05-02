package domainevent.publisher.airline;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Queue;

import domainevent.publisher.jmseventpublisher.BaseJMSEventPublisher;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import integration.producer.qualifiers.AirlineReservationQueue;
import msa.commons.consts.JMSQueueNames;

@Stateless
@JMSAirlineReservationPublisherQualifier
@Local(IEventPublisher.class)
public class JMSAirlineReservationPublisher extends BaseJMSEventPublisher {

    @Inject
    @Override
    public void setQueueInject(@AirlineReservationQueue Queue queueInject) {
        this.queue = queueInject;
    }

    @Override
    public String getQueueName() {
        return JMSQueueNames.AIRLINE_RESERVATION_QUEUE;
    }

}

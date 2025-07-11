package domainevent.publisher.travelqueue;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.jms.Queue;

import domainevent.publisher.jmseventpublisher.BaseJMSEventPublisher;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import integration.producer.qualifiers.TravelQueue;
import msa.commons.consts.JMSQueueNames;

@Stateless
@JMSTravelPublisherQualifier
@Local(IEventPublisher.class)
public class JMSTravelPublisher extends BaseJMSEventPublisher {
    @Inject
    @Override
    public void setQueueInject(@TravelQueue Queue typeUserServiceQueue) {
        this.queue = typeUserServiceQueue;
    }

    @Override
    public String getQueueName() {
        return JMSQueueNames.AGENCY_TYPE_USER_SERVICE_QUEUE;
    }
}

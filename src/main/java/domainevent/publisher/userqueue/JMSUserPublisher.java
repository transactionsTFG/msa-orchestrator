package domainevent.publisher.userqueue;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.jms.Queue;

import domainevent.publisher.jmseventpublisher.BaseJMSEventPublisher;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import integration.consts.JMSQueueNames;
import integration.producer.qualifiers.UserQueue;


@Stateless
@JMSUserPublisherQualifier
@Local(IEventPublisher.class)
public class JMSUserPublisher extends BaseJMSEventPublisher {

    @Inject
    @Override
    public void setQueueInject(@UserQueue Queue userServiceQueue) {
        this.queue = userServiceQueue;
    }

    @Override
    public String getQueueName() {
        return JMSQueueNames.USER_SERVICE_QUEUE;
    }
}

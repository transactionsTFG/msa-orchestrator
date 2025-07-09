package domainevent.publisher.arilineorchestratorqueue;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.jms.Queue;

import domainevent.publisher.jmseventpublisher.BaseJMSEventPublisher;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import integration.producer.qualifiers.OrchestratorAirlineQueue;
import msa.commons.consts.JMSQueueNames;


@Stateless
@JMSOrchestratorAirlinePublisherQualifier
@Local(IEventPublisher.class)
public class JMSAirlineOrchestratorPublisher extends BaseJMSEventPublisher {

    @Inject
    @Override
    public void setQueueInject(@OrchestratorAirlineQueue Queue userServiceQueue) {
        this.queue = userServiceQueue;
    }

    @Override
    public String getQueueName() {
        return JMSQueueNames.ORCHESTRATOR_AIRLINE_QUEUE;
    }
}

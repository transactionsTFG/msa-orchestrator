package domainevent.publisher.arilineorchestratorqueue;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.jms.Queue;

import domainevent.publisher.jmseventpublisher.BaseJMSEventAirlinePublisher;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import integration.producer.qualifiers.OrchestratorAirlineQueue;
import msa.commons.consts.JMSQueueNames;


@Stateless
@JMSOrchestratorAirlinePublisherQualifier
@Local(IEventPublisher.class)
public class JMSAirlineOrchestratorPublisher extends BaseJMSEventAirlinePublisher {

    @Inject
    @Override
    public void setQueueInject(@OrchestratorAirlineQueue Queue userServiceQueue) {
        this.queue = userServiceQueue;
    }

    @Override
    public String getQueueName() {
        return JMSQueueNames.REMOTE_ORCHESTRATOR_AIRLINE_QUEUE;
    }
}

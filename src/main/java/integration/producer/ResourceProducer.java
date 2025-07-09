package integration.producer;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import integration.producer.qualifiers.OrchestratorAirlineQueue;
import integration.producer.qualifiers.TypeUserQueue;
import integration.producer.qualifiers.UserQueue;
import msa.commons.consts.JMSQueueNames;

@ApplicationScoped
public class ResourceProducer {
    @Produces
    @Resource(lookup = "jms/connectionFactory")
    private ConnectionFactory connectionFactory;

    @Produces
    @Resource(lookup = JMSQueueNames.AGENCY_TYPE_USER_SERVICE_QUEUE)
    @TypeUserQueue
    private Queue typeUserServiceQueue;
    @Produces
    @Resource(lookup = JMSQueueNames.AGENCY_USER_SERVICE_QUEUE)
    @UserQueue
    private Queue userServiceQueue;
    
    @Produces
    @Resource(lookup = JMSQueueNames.ORCHESTRATOR_AIRLINE_QUEUE)
    @OrchestratorAirlineQueue
    private Queue orchestratorAirlineQueue;

}

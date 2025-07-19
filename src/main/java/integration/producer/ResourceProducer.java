package integration.producer;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import business.qualifier.cf.ConnectionFactoryAirlineQualifier;
import business.qualifier.cf.ConnectionFactoryLocalQualifier;
import integration.producer.qualifiers.OrchestratorAirlineQueue;
import integration.producer.qualifiers.TypeUserQueue;
import integration.producer.qualifiers.TravelQueue;
import integration.producer.qualifiers.UserQueue;
import msa.commons.consts.JMSQueueNames;

@ApplicationScoped
public class ResourceProducer {
    @Produces
    @ConnectionFactoryLocalQualifier
    @Resource(lookup = JMSQueueNames.CONNECTION_FACTORY_LOCAL)
    private ConnectionFactory connectionFactory;

    @Produces
    @ConnectionFactoryAirlineQualifier
    @Resource(lookup = JMSQueueNames.CONNECTION_FACTORY_AIRLINE)
    private ConnectionFactory connectionFactoryAirline;

    @Produces
    @Resource(lookup = JMSQueueNames.AGENCY_TYPE_USER_SERVICE_QUEUE)
    @TypeUserQueue
    private Queue typeUserServiceQueue;
    @Produces
    @Resource(lookup = JMSQueueNames.AGENCY_USER_SERVICE_QUEUE)
    @UserQueue
    private Queue userServiceQueue;
    
    @Produces
    @Resource(lookup = JMSQueueNames.REMOTE_ORCHESTRATOR_AIRLINE_QUEUE)
    @OrchestratorAirlineQueue
    private Queue orchestratorAirlineQueue;


    @Produces
    @Resource(lookup = JMSQueueNames.AGENCY_TRAVEL_SERVICE_QUEUE)
    @TravelQueue
    private Queue travelQueue;
}

package integration.producer;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import integration.consts.JMSQueueNames;
import integration.producer.qualifiers.TypeUserQueue;
import integration.producer.qualifiers.UserQueue;

@ApplicationScoped
public class ResourceProducer {
    @Produces
    @Resource(lookup = "jms/connectionFactory")
    private ConnectionFactory connectionFactory;

    @Produces
    @Resource(lookup = JMSQueueNames.TYPE_USER_SERVICE_QUEUE)
    @TypeUserQueue
    private Queue typeUserServiceQueue;

    @Produces
    @Resource(lookup = JMSQueueNames.USER_SERVICE_QUEUE)
    @UserQueue
    private Queue userServiceQueue;
}

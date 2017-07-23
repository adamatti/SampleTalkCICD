package adamatti.helper

abstract class RabbitMQHelper {
	static String buildQueueEndpoint(String queueName) {
		return "rabbitmq://localhost:5672/amq.topic?" +
				"queue=${queueName}&" +
				"exchangeType=topic&" +
				"durable=true&" +
				"routingKey=*&" +
				"autoDelete=false&"+
				"connectionFactory=#customConnectionFactory"
	}
}

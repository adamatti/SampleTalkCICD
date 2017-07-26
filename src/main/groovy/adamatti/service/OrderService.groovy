package adamatti.service

import adamatti.repo.OrderRepo
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.spring.SpringRouteBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import static adamatti.helper.RabbitMQHelper.buildQueueEndpoint

@Component
class OrderService extends SpringRouteBuilder implements Processor{
	@Autowired
	private OrderRepo orderRepo

	@Autowired
	private SendEmailService sendEmailService

	void configure() throws Exception {
		errorHandler(noErrorHandler())

		from(buildQueueEndpoint("orders"))
			.bean("orderService")

	}

	void process(Exchange exchange) throws Exception {
		Map order = exchange.in.body

		log.info "Order received: ${order}"

		orderRepo.save(order)

		sendEmailService.sendEmail(order: order)
	}
}

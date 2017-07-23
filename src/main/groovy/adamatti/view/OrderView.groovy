package adamatti.view

import adamatti.repo.OrderRepo
import org.apache.camel.ProducerTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import spark.Request
import spark.Response

import javax.annotation.PostConstruct

import static adamatti.helper.JsonHelper.toJsonMap
import static adamatti.helper.RabbitMQHelper.buildQueueEndpoint
import static adamatti.helper.SparkHelper.jsonGet
import static adamatti.helper.SparkHelper.jsonPost

@Component
class OrderView {
	@Autowired
	private OrderRepo orderRepo

	@Autowired
	private ProducerTemplate producerTemplate

	@PostConstruct
	void registerEndpoints(){
		jsonGet("/orders"){ Request req, Response res ->
			orderRepo.list(getQueryMap(req))
		}

		jsonGet("/orders/:id"){ Request req, Response res ->
			Map entity = orderRepo.list([_id: req.params("id")]).find{true}

			if (!entity){
				res.status(404)
			}

			entity
		}

		jsonPost("/orders"){Request req, Response res ->
			String newId = UUID.randomUUID().toString()

			Map entity = [_id: newId] + toJsonMap(req.body())

			producerTemplate.sendBody(buildQueueEndpoint("orders"), entity)

			entity
		}
	}

	private Map getQueryMap(Request request){
		request.queryMap().toMap().collectEntries {k, v -> [k, v.first()]}
	}
}

package adamatti.view

import adamatti.service.CircuitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import spark.Request
import spark.Response

import javax.annotation.PostConstruct

import static adamatti.helper.SparkHelper.jsonGet

@Component
class CircuitView {
	@Autowired
	private CircuitService service

	@PostConstruct
	void registerEndpoint(){
		jsonGet("/test"){ Request req, Response res ->
			service.testFail()
		}
	}

}

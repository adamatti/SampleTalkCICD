package adamatti

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import spock.lang.Specification

import static adamatti.helper.TestHelper.*

class OrderSpec extends Specification {
	private static final RESTClient client = buildRestClient()

	def "list orders"(){
		when:
			waitForApp()
			HttpResponseDecorator response = client.get(path:"/orders")
		then:
			response.status == 200
			response.data instanceof List
	}

	def "place order"(){
		given:
			Map payload = [user: "marcelo"]
		when:
			waitForApp()
			HttpResponseDecorator response = client.post(path:"/orders",body: payload)
			def id = response.data._id
		then:
			response.status == 200
			id != null

			waitFor(10,"order was not processed [_id: ${id}"){
				Map res = client.get(path: "/orders/${id}").data

				res._id == id && res.user == payload.user
			}
	}
}

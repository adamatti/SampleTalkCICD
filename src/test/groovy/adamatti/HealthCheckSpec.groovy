package adamatti

import adamatti.helper.ConfigHelper
import groovy.util.logging.Slf4j
import groovyx.net.http.HttpResponseDecorator
import spock.lang.Specification

import static adamatti.helper.TestHelper.buildRestClient
import static adamatti.helper.TestHelper.waitForApp

@Slf4j
class HealthCheckSpec extends Specification {
	private static final ConfigObject cfg = ConfigHelper.cfg

	def "test health check"(){
		given:
			def client = buildRestClient()
		when:
			waitForApp()
			HttpResponseDecorator response = client.get(path: "/healthCheck")
		then:
			response.data.status == "ok"
	}
}

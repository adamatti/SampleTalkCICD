package adamatti

import adamatti.helper.ConfigHelper
import groovy.util.logging.Slf4j
import groovyx.net.http.RESTClient
import spock.lang.Specification

@Slf4j
class HealthCheckSpec extends Specification {
	def "test health check"(){
		given:
			def cfg = ConfigHelper.cfg
			def testUrl = "http://${cfg.test.host}:${cfg.test.port}"
			def client = new RESTClient(testUrl,"application/json")
		when:
			waitFor(10,"App not available [testUrl: ${testUrl}]"){
				new Socket(cfg.test.host,cfg.test.port as int).close()
				true
			}
			def response = client.get(path: "/healthCheck")
		then:
			response.data.status == "ok"
	}

	private void waitFor(int tentatives, String errorMsg, Closure code){
		int tentative = 1

		while(tentative < tentatives){
			log.info "Waiting [tentative: ${tentative}]"
			try {
				boolean result = code.call()
				if (result) {
					return
				}
			} catch (Exception e){
				log.warn "Error: ${e.message}"
			}
			Thread.sleep(1000)
			tentative++
		}

		throw new Exception(errorMsg)
	}
}

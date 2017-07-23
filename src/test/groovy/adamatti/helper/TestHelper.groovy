package adamatti.helper

import groovy.util.logging.Slf4j
import groovyx.net.http.RESTClient

@Slf4j
abstract class TestHelper {
	private static final ConfigObject cfg = ConfigHelper.cfg

	static RESTClient buildRestClient(){
		def testUrl = "http://${cfg.test.host}:${cfg.test.port}"
		def client = new RESTClient(testUrl,"application/json")
		client
	}

	static void waitForApp(){
		waitFor(10,"App not available [host: ${cfg.test.host}, port: ${cfg.test.port}]"){
			new Socket(cfg.test.host,cfg.test.port as int).close()
			true
		}
	}

	static void waitFor(int tentatives, String errorMsg, Closure code){
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

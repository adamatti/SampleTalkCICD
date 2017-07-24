package adamatti.service

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class CircuitService {
	private static final Random random = new Random()

	@HystrixCommand(fallbackMethod = "testOk")
	String testFail(){
		if (random.nextBoolean()){
			log.warn "Call failed"
			throw new Exception("fail")
		}
		"first call worked"
	}

	private String testOk(){
		log.info "fallback called"

		"ok"
	}
}

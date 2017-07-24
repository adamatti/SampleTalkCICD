package adamatti

import adamatti.helper.ConfigHelper
import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.stereotype.Component

import static adamatti.helper.SparkHelper.startSpark

@Slf4j
@Component
@Configuration
@EnableCircuitBreaker
@SpringBootApplication
@ImportResource("classpath:/spring/root.groovy")
class Main {
	private static ConfigObject cfg = ConfigHelper.cfg

	static void main(String [] args){
		log.info "Starting"
		startSpark(cfg.web.port as int)

		SpringApplication.run(Main.class)
		log.info "Started"
	}

}

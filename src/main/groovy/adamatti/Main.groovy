package adamatti

import adamatti.helper.ConfigHelper
import groovy.util.logging.Slf4j
import org.springframework.context.ApplicationContext
import org.springframework.context.support.GenericGroovyApplicationContext

import static adamatti.helper.SparkHelper.startSpark

@Slf4j
class Main {
	private static ConfigObject cfg = ConfigHelper.cfg

	static void main(String [] args){
		new Main().run()
	}

	private void run(){
		log.info "Starting"
		startSpark(cfg.web.port as int)
		startSpring()
		log.info "Started [port: ${cfg.web.port}]"
	}

	private ApplicationContext startSpring(){
		def context = new GenericGroovyApplicationContext("classpath:/spring/root.groovy")
		context.registerShutdownHook()
		context
	}
}

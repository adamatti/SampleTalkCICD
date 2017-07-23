package adamatti

import groovy.util.logging.Slf4j
import org.springframework.context.ApplicationContext
import org.springframework.context.support.GenericGroovyApplicationContext

@Slf4j
class Main {
	static void main(String [] args){
		new Main().run()
	}

	private void run(){
		log.info "Starting"
		startSpring()
		log.info "Started"
	}

	private ApplicationContext startSpring(){
		def context = new GenericGroovyApplicationContext("classpath:/spring/root.groovy")
		context.registerShutdownHook()
		context
	}
}

package adamatti

import groovy.util.logging.Slf4j
import spock.lang.Specification
import spock.lang.Unroll

@Slf4j
class BusinessRulesSpec extends Specification {
	@Unroll
	def "send email for #label"() {
		given:
			def rule = """
				if (order.region == 'eame') {
					return true
				} else {
					return false
				}
			"""
		when:
			def result = evaluateLogic(ctx, rule)
		then:
			result == expected
		where:
			label  | ctx                       | expected
			'eame' | [order: [region: "eame"]] | true
			'amer' | [order: [region: "amer"]] | false
	}

	private boolean evaluateLogic(Map ctx, String rule){
		log.info "Start evaluation"

		def binding = new Binding(ctx)
		def shell = new GroovyShell(binding)
		def result = shell.evaluate(rule)

		log.info "End evaluation [result: ${result}]"

		result
	}
}

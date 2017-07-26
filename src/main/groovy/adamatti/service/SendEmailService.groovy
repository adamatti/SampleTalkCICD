package adamatti.service

import adamatti.helper.ConfigHelper
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class SendEmailService {
	private static final ConfigObject cfg = ConfigHelper.cfg

	void sendEmail(Map args){
		if (cfg.toggles.sendEmail){
			//TODO some logic here
			// We could have a dynamic logic here, check BusinessRulesSpec
			log.info "Email sent"
		}
	}
}

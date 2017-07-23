package spring

beans {
	xmlns context:"http://www.springframework.org/schema/context"
	xmlns camel:"http://camel.apache.org/schema/spring"

	camel.camelContext(id: "camelContext", autoStartup:true){
		camel.contextScan()
	}

	customConnectionFactory(com.rabbitmq.client.ConnectionFactory.class){
		uri  = '${mq.url}'
		automaticRecoveryEnabled = true
		requestedHeartbeat = 60
	}
}

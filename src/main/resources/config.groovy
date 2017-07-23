web = [
	port: (System.env.PORT ?: "8080").toInteger()
]

test = [
	host: System.env.TEST_HOST ?: "localhost",
	port: (System.env.TEST_PORT ?: "${web.port}").toInteger()
]

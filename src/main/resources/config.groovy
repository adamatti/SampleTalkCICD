mongo = [
	url : System.env.MONGO_URL ?: "mongodb://localhost:27017/sample"
]

mq = [
	url : System.env.MQ_URL ?: "amqp://admin:admin@localhost/admin",
]

web = [
	port: (System.env.PORT ?: "8080").toInteger()
]

test = [
	host: System.env.TEST_HOST ?: "localhost",
	port: (System.env.TEST_PORT ?: "${web.port}").toInteger()
]

toggles = [
	sendEmail : (System.env.SEND_EMAIL ?: "true").toBoolean()
]

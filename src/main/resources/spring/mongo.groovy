package spring

beans {
	xmlns context:"http://www.springframework.org/schema/context"
	xmlns mongo: "http://www.springframework.org/schema/data/mongo"

	mongoUri(com.mongodb.MongoClientURI,'${mongo.url}')

	mongoDbFactory(org.springframework.data.mongodb.core.SimpleMongoDbFactory, ref('mongoUri'))

	mongoTemplate(org.springframework.data.mongodb.core.MongoTemplate,ref('mongoDbFactory'))

	mongoExceptionTranslator(org.springframework.data.mongodb.core.MongoExceptionTranslator)
}

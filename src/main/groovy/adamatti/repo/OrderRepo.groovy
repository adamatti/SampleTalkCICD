package adamatti.repo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class OrderRepo {
	private static final String COLLECTION_NAME = "orders"

	@Autowired
	private MongoTemplate mongoTemplate

	List list(Map mapCriteria = [:]) {
		Criteria criteria = toCriteria(mapCriteria)
		list(criteria)
	}

	private Criteria toCriteria(Map map) {
		Criteria criteria = new Criteria()

		map.each {k, v ->
			criteria = criteria.and(k).is(v)
		}

		criteria
	}

	List list(Criteria criteria){
		Query query = new Query(criteria)
		list(query)
	}

	List list(Query query){
		mongoTemplate.find(query,Map.class,COLLECTION_NAME)
	}

	Map save(Map entity){
		mongoTemplate.save(entity,COLLECTION_NAME)
		entity
	}
}

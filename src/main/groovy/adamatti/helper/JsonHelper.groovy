package adamatti.helper

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper


abstract class JsonHelper {
	static String toJsonString(Object obj){
		new JsonBuilder(obj).toPrettyString()
	}

	static Map toJsonMap(String str){
		new JsonSlurper().parseText(str)
	}
}

package adamatti.helper

import groovy.json.JsonBuilder


abstract class JsonHelper {
	static String toJsonString(Object obj){
		new JsonBuilder(obj).toPrettyString()
	}
}

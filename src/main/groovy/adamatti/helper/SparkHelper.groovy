package adamatti.helper

import spark.Request
import spark.Response
import spark.Spark

import static adamatti.helper.JsonHelper.toJsonString

class SparkHelper {
	static void startSpark(int port = 8080){
		Spark.port(port)

		jsonGet("/healthCheck"){Request req, Response res ->
			[status: "ok", date: new Date()]
		}
	}

	static void jsonGet(String path, Closure code){
		Spark.get(path){Request req, Response res ->
			res.header("Content-Type","application/json")
			toJsonString code.call(req, res)
		}
	}
}

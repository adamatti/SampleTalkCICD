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
		jsonMethod(Spark.&get,path,code)
	}

	static void jsonPost(String path, Closure code){
		jsonMethod(Spark.&post,path,code)
	}

	static void jsonMethod(Closure method, String path, Closure code){
		method(path){Request req, Response res ->
			res.header("Content-Type","application/json")

			try {
				toJsonString code.call(req, res)
			} catch (Exception e){
				res.status(500)
				toJsonString([error: e.message])
			}
		}
	}
}

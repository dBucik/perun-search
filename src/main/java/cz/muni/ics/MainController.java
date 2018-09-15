package cz.muni.ics;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@RequestMapping("/graphql")
	public String process(@RequestParam(value = "query") String queryString) {
		JSONObject queryJson = new JSONObject(queryString);

		//TODO: walk through the JSON and create query from it
		//TODO: execute query and return the result

		return null;
	}
}

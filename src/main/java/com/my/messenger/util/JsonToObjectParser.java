package com.my.messenger.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonToObjectParser {
	
	public JSONObject jsonToObject(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(jsonStr);			
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return obj;
	}
	

}

package com.vtron.lc.controller;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vtron.lc.common.HttpRequest;

@Controller
@RequestMapping("InternationalTrade")
public class InternationalTradeController {

	@RequestMapping(value = "/declare", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject declare(String url,/*
										 * String clientId, String key, String
										 * messageType,
										 */String messageText) throws DocumentException {
		// SAXReader reader = new SAXReader();
		// System.out.println(messageText);
		// messageText = URLEncoder.encode(messageText, "UTF-8");
		// 发送 POST 请求
		/*
		 * String sr = HttpRequest.sendPost(url, "clientId=" + clientId +
		 * "&key=" + key + "&messageType=" + messageType + "&messageText=" +
		 * messageText);
		 */
		String sr = HttpRequest.sendPost(url, "messageText=" + messageText);
		System.out.println(sr);
		JSONObject result = JSONObject.fromObject(sr);
		// System.out.println(result.getString("description"));
		// Document document =
		// DocumentHelper.parseText(result.getString("description"));
		// String description = document.asXML();
		// System.out.println(description);
		// result.put("description", description);
		return result;

	}
}

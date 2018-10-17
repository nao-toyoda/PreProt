package com.controller.MyAccount;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@ComponentScan("com.controller")
public class MyAccountController {
	private static final int STATUS_OK = 0;
	private static final int STATUS_NG = 1;
	private static final String P_SITE_ID = "tsite00031677";
	private static final String P_SITE_PASS = "xagd8q5n";

	private class ResultInfo{
		public String memberId;
		public String memberName;
		public int status;
		public String error;
	}
	@RequestMapping("/MyAccount")
	public String MyAccount() {

		return "MyAccount/MyAccount";
	}
	@RequestMapping("/MyAccount/GetAccountInfo")
	@ResponseBody
	public String GetAccountInfo(String MemberId) {
		ResultInfo ret = new  ResultInfo();
		ret.memberId = "test";
		ret.memberName = "豊田　尚彦";
		ret.status = STATUS_OK;

		String retVal = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			retVal = mapper.writeValueAsString(ret);
		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			ret.status = STATUS_NG;
			ret.error = e.getMessage();
		}
		return retVal;
	}
}

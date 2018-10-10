package com.example.demo;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmo_pg.g_pay.client.common.PaymentException;
import com.gmo_pg.g_pay.client.impl.PaymentClientImpl;
import com.gmo_pg.g_pay.client.input.SearchMemberInput;
import com.gmo_pg.g_pay.client.output.ErrHolder;
import com.gmo_pg.g_pay.client.output.SearchMemberOutput;
import com.gmo_pg.g_pay.client.output.SearchMemberOutput.MemberInfo;

@Controller
@EnableAutoConfiguration
public class GmoController {
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
	@RequestMapping("/")
	//@ResponseBody
	public ModelAndView GmoMember() {
		ModelAndView mav = new ModelAndView();
        mav.setViewName("member");
        return mav;
	}
	/*
	public String GmoMember() {
        return "Hello, Spring Boot Sample Application!";
	}
*/

	@ResponseBody
	@RequestMapping("/GetGmoMemberInfo")
	public String GetGmoMemberInfo(String MemberId) {
		ResultInfo ret = new  ResultInfo();
		ret.memberId = MemberId;
		ret.memberName = "";
		ret.status = STATUS_OK;

		String retVal = "";

		PaymentClientImpl client = new PaymentClientImpl();
		SearchMemberInput input = new SearchMemberInput();
		SearchMemberOutput output = null;

		// パラメータの設定
		input.setSiteId(P_SITE_ID);
		input.setSitePass(P_SITE_PASS);
		input.setMemberId(MemberId);

		try {
			// API呼び出し
			output = client.doSearchMember(input);
		} catch (PaymentException e) {
			//
			// [注意事項]
			// エラーが発生した場合は、お手数ですが加盟店様側でエラー処理を行っていただくようお願い致します。
			//
			ret.status = STATUS_NG;
			ret.error = e.getMessage();
		}

		// 結果確認
		if (output.isErrorOccurred()) {

			List list = output.getErrList();

			ret.status = STATUS_NG;

			for(Iterator it = list.iterator(); it.hasNext();) {
				ErrHolder eh = (ErrHolder)it.next();
				ret.error = MessageFormat.format("ErrorCode: {0}  ErrorInfo: {1}",
								new Object[]{eh.getErrCode(), eh.getErrInfo()});
				break;
			}
		} else {
			List list = output.getMemberList();

			for(Iterator it = list.iterator(); it.hasNext();) {
				MemberInfo info = (MemberInfo)it.next();

				ret.memberId = info.getMemberId();
				ret.memberName = info.getMemberName();

				break;
			}
		}


		ObjectMapper mapper = new ObjectMapper();
		try {
			retVal = mapper.writeValueAsString(ret);
		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return retVal;
	}
	@ResponseBody
	@RequestMapping("/ReciveCreditEditResult")
	public String ReciveCreditEditResult() {
		return "creditEditResult";
	}
}

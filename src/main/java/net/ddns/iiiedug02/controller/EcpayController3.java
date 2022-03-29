package net.ddns.iiiedug02.controller;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ecpay.payment.integration.AllInOne;

//本控制器用以接收付款者在付款成功後，從用戶端回傳的付款結果。
@Controller
public class EcpayController3 {
	public static AllInOne all = new AllInOne("");	
	
	//【ECPayServer.java】obj.setClientBackURL("http://localhost:8080/ecpay/ECPayServer3");
	//綠界付款成功畫面會增加「返回商店」按鈕，消費者點選此按鈕後，會將頁面導回到此設定的網址
	//(注意：導回時不會帶付款結果到此網址，只是將頁面(以GET方法)導回而已。)	
	@GetMapping(value="/ECPayServer3", produces="text/html;charset=utf-8")	  
	@ResponseBody public String processPaymentResult() { 
		return  "<h1>恭喜您付款成功。</h1>"; 
	}	 
	
	//【ECPayServer.java】obj.setOrderResultURL("http://localhost:8080/ecpay/ECPayServer3"); 
	//當消費者付款完成後，綠界會將付款結果參數以幕前(Client POST)回傳到該網址。			
	//若與[ClientBackURL]同時設定，將會以此參數為主。
	@PostMapping(value="/ECPayServer3",  produces="text/html;charset=utf-8") //預設response的字元編碼為ISO-8859-1
	@ResponseBody
	public String processPaymentResult2(HttpServletRequest request) {		
		
		Hashtable<String, String> dict = new Hashtable<String, String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()) {
			String paramName = enumeration.nextElement();
			String paramValue = request.getParameter(paramName);
			dict.put(paramName, paramValue);			
		}
		System.out.printf("【ECPayServer3.java】用戶端付款成功後回傳「付款結果」通知給伺服端的參數們：\n%s\n", dict.toString());
		//輸出範例：
		//【ECPayServer3.java】用戶端付款成功後回傳「付款結果」通知給伺服端的參數們：
		//{CheckMacValue=028D288F5CB566EB1FA5E204FA46B6FC68AB3ED68EC12AE17E561A6A9AF885F5, 
		//TradeDate=2021/08/31 11:09:08, TradeNo=2108311109087900, MerchantID=2000132, 
		//PaymentTypeChargeFee=21, PaymentType=Credit_CreditCard, TradeAmt=1050, RtnMsg=Succeeded, StoreID=, CustomField4=, 
		//MerchantTradeNo=III1630379348465, CustomField3=, 
		//PaymentDate=2021/08/31 11:10:23, SimulatePaid=0, CustomField2=, CustomField1=, RtnCode=1}

		boolean checkStatus = all.compareCheckMacValue(dict); //true：表示資料未被竄改
		//消費者付款成功且檢查碼正確的時候：	(RtnCode:交易狀態(1:成功，其餘為失敗))	
		if ("1".equals(dict.get("RtnCode")) && checkStatus==true ){
			//---------------------------//
			//在此撰寫你的處理邏輯
			//---------------------------//
			//回應用戶端(付款者)
			return "<h1>伺服端已接收到從用戶端(付款者)送出的「付款成功」通知。</h1>";			 
		}
		else
			return "<h1>伺服端已接收到從用戶端(付款者)送出的付款通知(但付款資料有誤！)。</h1>";	
	}
}

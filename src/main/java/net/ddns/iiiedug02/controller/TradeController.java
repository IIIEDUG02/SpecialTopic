package net.ddns.iiiedug02.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import net.ddns.iiiedug02.exception.NotLoginException;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.ClassManagementBean;
import net.ddns.iiiedug02.model.bean.EcpayRecord;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.ShoppingCart;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.ClassManagementService;
import net.ddns.iiiedug02.model.service.EcpayRecordService;
import net.ddns.iiiedug02.model.service.MemberService;
import net.ddns.iiiedug02.model.service.ShoppingCartService;

@Controller
public class TradeController {

    @Autowired
    private EcpayRecordService ecpayRecordService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ClassBeanService classService;

    @Autowired
    private ClassManagementService classManagementService;

    private static AllInOne all = new AllInOne("");

    // 將購物車資料送至綠界api-1
    @RequestMapping(value = "ECPayServer", produces = "text/html;charset=utf-8") // 預設response的字元編碼為ISO-8859-1
    @ResponseBody
    public String processPayment(HttpServletRequest request, HttpSession session) {
        EcpayRecord er = new EcpayRecord();
        String merchantTradeNo = String.format("J123G02%d", new Date().getTime());

        er.setCids(request.getParameter("CidList"));
        er.setMember((Member) session.getAttribute("loginBean"));
        // er.setOrderDate(new java.sql.Date(new Date().getTime()));
        er.setOrderId(merchantTradeNo);
        er.setTradeAmount(Integer.parseInt(request.getParameter("TotalAmount")));
        ecpayRecordService.save(er);

        return genAioCheckOutALL(request, merchantTradeNo);
    }

    // 將購物車資料送至綠界api-2
    private String genAioCheckOutALL(HttpServletRequest request, String merchantTradeNo) {
        AioCheckOutALL obj = new AioCheckOutALL();
        // 下列三項設定於payment_conf.xml
        // <MerchantID>2000132</MerchantID>
        // <HashKey>5294y06JbISpM5x9</HashKey>
        // <HashIV>v77hoKGq4kWxNNIS</HashIV>
        // obj.setMerchantTradeNo(String.format("J123G02%d", new Date().getTime())); //
        // 特店交易編號均為唯一值，不可重複使用。英數字大小寫混合。
        obj.setMerchantTradeNo(merchantTradeNo);
        obj.setMerchantTradeDate(String.format("%tY/%<tm/%<td %<tH:%<tM:%<tS", new Date())); // 特店交易時間。格式為：yyyy/MM/dd
                                                                                             // HH:mm:ss。

        obj.setTotalAmount(request.getParameter("TotalAmount")); // 交易金額。請帶整數，不可有小數點。僅限新台幣。
        obj.setTradeDesc(request.getParameter("TradeDesc")); // 交易描述。請勿帶入特殊字元。
        obj.setItemName(request.getParameter("ItemName")); // 商品名稱
                                                           // 1.
                                                           // 如果商品名稱有多筆，需在金流選擇頁一行一行顯示商品名稱的話，商品名稱請以符號#分隔。
                                                           // 2. 商品名稱字數限制為中英數 400 字內，超過此限制系統將自動截斷。
        obj.setNeedExtraPaidInfo("N");


        // ***付款結果通知我們伺服端的方式(可二選一)***//
        // A.以Server端(綠界)方式直接回傳付款結果通知
        obj.setReturnURL("https://iiiedug02.nilm.in/SpecialTopic/ECPayServer2");
        // 當消費者付款完成後，綠界會將付款結果參數以幕後(Server POST)回傳到該網址(該網址須是一個固定IP且使用https協定)。
        // 必設欄位。但我們可以忽略相關的後續處理作業。
        // B.以Client端(消費者)方式回傳付款結果通知
        obj.setOrderResultURL("http://localhost:8080/SpecialTopic/getEcPayResult");
        // 當消費者付款完成後，綠界會將付款結果參數以幕前(Client POST)回傳到該網址。
        // 若與下面[ClientBackURL]同時設定，將會以此參數為主。
        // ********************************//

        // obj.setClientBackURL("http://localhost:8080/ecpay/ECPayServer3");
        // 綠界付款成功畫面會增加「返回商店」按鈕，消費者點選此按鈕後，會將頁面導回到此設定的網址
        // (注意：導回時不會帶付款結果到此網址，只是將頁面(以GET方法)導回而已。)

        return all.aioCheckOut(obj, null);
    }

    // 【ECPayServer.java】obj.setOrderResultURL("http://localhost:8080/ecpay/ECPayServer3");
    // 當消費者付款完成後，綠界會將付款結果參數以幕前(Client POST)回傳到該網址。
    // 若與[ClientBackURL]同時設定，將會以此參數為主。
    @PostMapping(value = "/getEcPayResult", produces = "text/html;charset=utf-8") // 預設response的字元編碼為ISO-8859-1
    public String processPaymentResult2(HttpServletRequest request, Principal p, Model m) {

        Member loginBean = memberService.findByUsername(p.getName());
        Hashtable<String, String> dict = new Hashtable<String, String>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = enumeration.nextElement();
            String paramValue = request.getParameter(paramName);
            dict.put(paramName, paramValue);
        }

        boolean checkStatus = all.compareCheckMacValue(dict); // true：表示資料未被竄改
        // 消費者付款成功且檢查碼正確的時候： (RtnCode:交易狀態(1:成功，其餘為失敗))
        if ("1".equals(dict.get("RtnCode")) && checkStatus) {

            // 交易ID
            EcpayRecord er = ecpayRecordService.findByOrderId(dict.get("MerchantTradeNo"));
            java.sql.Date orderDate = new java.sql.Date(new Date().getTime());

            er.setPaymentType(dict.get("PaymentType"));
            er.setRtnMsg(dict.get("RtnMsg"));
            er.setOrderDate(orderDate);
            ecpayRecordService.save(er);

            if ("Succeeded".equals(dict.get("RtnMsg"))) {
                String[] cidStrList = er.getCids().split("#");
                List<ShoppingCart> scList = shoppingCartService.findAllByUid(loginBean.getUid());
                List<ClassManagementBean> cmbList = new ArrayList<ClassManagementBean>();
                // 刪除購物車 & 寫入ClassManagement
                for (String cidStr : cidStrList) {
                    int cidInt = Integer.parseUnsignedInt(cidStr);

                    ClassManagementBean cmb = new ClassManagementBean();
                    cmb.setCid(cidInt);
                    cmb.setUid(loginBean.getUid());
                    cmb.setOrderDate(orderDate);
                    cmb.setTid(dict.get("MerchantTradeNo"));
                    cmbList.add(cmb);
                }
                shoppingCartService.deleteByList(scList);
                classManagementService.insertByList(cmbList);
                m.addAttribute("msg", "交易成功");
            } else {
                m.addAttribute("msg", "交易失敗");
            }
        } else {
            m.addAttribute("msg", "交易發生錯誤");
        }
        return "redirect:/class/list";
    }

    @GetMapping("tradeRecord/teacher")
    public String tradeRecordTeacher(Principal p, Model m) {
        if (null == p) {
            throw new NotLoginException();
        }
        Member mb = memberService.findByUsername(p.getName());

        List<ClassBean> classList = classService.findAllByUid(mb.getUid());
        Map<ClassBean, List<ClassManagementBean>> class_cmbList_Map =
                new HashMap<ClassBean, List<ClassManagementBean>>();

        for (ClassBean classBean : classList) {
            List<ClassManagementBean> tradeList =
                    classManagementService.findByCid(classBean.getCid());
            class_cmbList_Map.put(classBean, tradeList);
        }

        m.addAttribute("class_cmbList_Map", class_cmbList_Map);
        return "tradeRecord/teacher";

    }

}



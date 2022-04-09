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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    /*
     * 接收form表單資料，新增資料至綠界交易紀錄，使用genAioCheckOutALL()
     */
    @RequestMapping(value = "ECPayServer", produces = "text/html;charset=utf-8") // 預設response的字元編碼為ISO-8859-1
    @ResponseBody
    public String processPayment(HttpServletRequest request, HttpSession session) {
        EcpayRecord er = new EcpayRecord();

        String merchantTradeNo = String.format("J123G02%d", new Date().getTime());
        er.setCids(request.getParameter("CidList"));
        er.setMember((Member) session.getAttribute("loginBean"));
        er.setOrderId(merchantTradeNo);
        er.setTradeAmount(Integer.parseInt(request.getParameter("TotalAmount")));
        ecpayRecordService.save(er);

        return genAioCheckOutALL(request, merchantTradeNo);
    }

    // 將購物車資料送至綠界api-2
    private String genAioCheckOutALL(HttpServletRequest request, String merchantTradeNo) {
        AioCheckOutALL obj = new AioCheckOutALL();

        obj.setMerchantTradeNo(merchantTradeNo);
        obj.setMerchantTradeDate(String.format("%tY/%<tm/%<td %<tH:%<tM:%<tS", new Date()));
        obj.setTotalAmount(request.getParameter("TotalAmount"));
        obj.setTradeDesc(request.getParameter("TradeDesc"));
        obj.setItemName(request.getParameter("ItemName"));
        obj.setNeedExtraPaidInfo("N");
        obj.setReturnURL("https://iiiedug02.nilm.in/SpecialTopic/ECPayServer2");
        obj.setOrderResultURL("http://localhost:8080/SpecialTopic/getEcPayResult");

        return all.aioCheckOut(obj, null);
    }

    // 【ECPayServer.java】obj.setOrderResultURL("http://localhost:8080/ecpay/ECPayServer3");
    // 當消費者付款完成後，綠界會將付款結果參數以幕前(Client POST)回傳到該網址。
    // 若與[ClientBackURL]同時設定，將會以此參數為主。
    @PostMapping(value = "/getEcPayResult", produces = "text/html;charset=utf-8") // 預設response的字元編碼為ISO-8859-1
    public String processPaymentResult2(HttpServletRequest request, Principal p,
            RedirectAttributes attr) {

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
                attr.addAttribute("msg", "交易成功");
            } else {
                attr.addAttribute("msg", "交易失敗");
            }
        } else {
            attr.addAttribute("msg", "交易發生錯誤");
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



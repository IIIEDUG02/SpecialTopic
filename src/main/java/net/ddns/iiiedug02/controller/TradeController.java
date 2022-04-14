package net.ddns.iiiedug02.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import net.ddns.iiiedug02.annotation.AspectLogAnnotation;
import net.ddns.iiiedug02.model.bean.ClassManagementBean;
import net.ddns.iiiedug02.model.bean.EcpayRecord;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.ShoppingCart;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.ClassManagementService;
import net.ddns.iiiedug02.model.service.EcpayRecordService;
import net.ddns.iiiedug02.model.service.MemberService;
import net.ddns.iiiedug02.model.service.ShoppingCartService;
import net.ddns.iiiedug02.util.UniversalTool;

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
    @Autowired
    private UniversalTool utool;
    @Autowired
    private AllInOne all;

    /*
     * 接收form表單資料，新增交易紀錄
     * 
     * @author Nilm
     */
    @AspectLogAnnotation
    @RequestMapping(value = "ECPayServer", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String processPayment(HttpServletRequest request, Principal p) {
        EcpayRecord er = new EcpayRecord();

        String merchantTradeNo = String.format("J123G02%d", new Date().getTime());
        er.setCids(request.getParameter("CidList"));
        er.setMember(utool.getLoiginBean(request.getSession(), p));
        er.setOrderId(merchantTradeNo);
        er.setTradeAmount(Integer.parseInt(request.getParameter("TotalAmount")));
        ecpayRecordService.save(er);

        return genAioCheckOutALL(request, merchantTradeNo);
    }

    /*
     * 接收form表單資料，呼叫綠界API
     * 
     * @author Nilm
     */
    private String genAioCheckOutALL(HttpServletRequest request, String merchantTradeNo) {
        AioCheckOutALL obj = new AioCheckOutALL();

        obj.setMerchantTradeNo(merchantTradeNo);
        obj.setMerchantTradeDate(String.format("%tY/%<tm/%<td %<tH:%<tM:%<tS", new Date()));
        obj.setTotalAmount(request.getParameter("TotalAmount"));
        obj.setTradeDesc(request.getParameter("TradeDesc"));
        obj.setItemName(request.getParameter("ItemName"));
        obj.setNeedExtraPaidInfo("N");

        obj.setReturnURL("https://iiiedug02.nilm.in/SpecialTopic/ECPayServer2132");
        obj.setOrderResultURL("http://localhost:8080/SpecialTopic/getEcPayResult");
        // obj.setOrderResultURL("https://iiiedug02.nilm.in/SpecialTopic/getEcPayResult");
        return all.aioCheckOut(obj, null);
    }

    /*
     * 接收綠界回傳的資料
     * 
     * @author Nilm
     */
    @PostMapping(value = "/getEcPayResult", produces = "text/html;charset=utf-8")
    @AspectLogAnnotation
    public String processPaymentResult2(HttpServletRequest request, Principal p,
            RedirectAttributes attr) {

        Member loginBean = utool.getLoiginBean(request.getSession(), p);
        Hashtable<String, String> dict = new Hashtable<String, String>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = enumeration.nextElement();
            String paramValue = request.getParameter(paramName);
            dict.put(paramName, paramValue);
        }

        boolean checkStatus = all.compareCheckMacValue(dict);

        if ("1".equals(dict.get("RtnCode")) && checkStatus) {

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
}

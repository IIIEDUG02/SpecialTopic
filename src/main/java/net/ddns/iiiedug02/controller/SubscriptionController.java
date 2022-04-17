package net.ddns.iiiedug02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.ddns.iiiedug02.model.bean.SubscriptionBean;
import net.ddns.iiiedug02.model.service.SubscriptionService;

@Controller
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("addSubscript")
    @ResponseBody
    public String addSubscript(@RequestParam("email") String email) {
        SubscriptionBean sb = new SubscriptionBean();
        sb.setEmail(email);
        subscriptionService.save(sb);
        return "success";
    }
}

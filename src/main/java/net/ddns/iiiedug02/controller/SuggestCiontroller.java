package net.ddns.iiiedug02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import net.ddns.iiiedug02.model.bean.Suggest;
import net.ddns.iiiedug02.model.service.SuggestService;

@Controller
public class SuggestCiontroller {

    @Autowired
    private SuggestService suggestService;

    @PostMapping("Suggest/api/add")
    @ResponseBody
    public String sendSuggest(@RequestBody Suggest suggest) {
        System.out.println(suggest);
        suggestService.save(suggest);
        return "123";
    }
}

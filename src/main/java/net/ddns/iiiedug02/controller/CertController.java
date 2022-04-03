package net.ddns.iiiedug02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import net.ddns.iiiedug02.model.bean.CertificationBean;
import net.ddns.iiiedug02.model.service.CertificationService;

@Controller
public class CertController {

    @Autowired
    private CertificationService certService;

    @GetMapping("getCertByCertId/{certId}")
    @ResponseBody
    public CertificationBean getCert(@PathVariable("certId") int certId) {
        return certService.findByCertId(certId);
    }

    @GetMapping("CertList")
    public String getCertList(Model m) {
        m.addAttribute("certList", certService.findAll());
        return "cert/certList";
    }
}

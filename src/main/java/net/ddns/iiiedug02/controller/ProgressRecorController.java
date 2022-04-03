package net.ddns.iiiedug02.controller;

import java.security.Principal;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.ddns.iiiedug02.exception.NotLoginException;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.ProgressRecord;
import net.ddns.iiiedug02.model.service.ProgressRecordService;
import net.ddns.iiiedug02.util.UniversalTool;

/*
 * 學生影片紀錄的Controller
 */
@Controller
public class ProgressRecorController {

    @Autowired
    private UniversalTool uttool;

    @Autowired
    private ProgressRecordService progressRecordService;

    @GetMapping("ProgressRecord/api/{cuid}")
    @ResponseBody
    public ProgressRecord getRecord(HttpSession session, Principal p,
            @PathVariable("cuid") int cuid) {
        Member loginBean = null;

        // NotLoginException會直接倒回首頁，所以這邊要先擷取。
        try {
            loginBean = uttool.getLoiginBean(session, p);
        } catch (NotLoginException e) {
            return null;
        }

        if (null == loginBean) {
            throw new NotLoginException();
        }
        return progressRecordService.findByUidAndCuid(loginBean.getUid(), cuid);
    }


    @PostMapping("ProgressRecord/api/{cuid}")
    @ResponseBody
    public String saveRecord(HttpSession session, Principal p, @PathVariable("cuid") int cuid,
            @RequestParam Map<String, String> params) {
        Member loginBean = null;
        // NotLoginException會直接倒回首頁，所以這邊要先擷取。
        try {
            loginBean = uttool.getLoiginBean(session, p);
        } catch (NotLoginException e) {
            return e.getMessage();
        }

        ProgressRecord record = progressRecordService.findByUidAndCuid(loginBean.getUid(), cuid);
        if (null == record) {
            record = new ProgressRecord();
            record.setCuid(cuid);
            record.setUid(loginBean.getUid());
        }

        float sumTime = Float.parseFloat(params.get("sumTime"));
        float duration = Float.parseFloat(params.get("duration"));
        boolean ended = Boolean.parseBoolean(params.get("ended"));

        record.setTimeSum(sumTime + record.getTimeSum());

        if (record.getTimeSum() >= duration && ended) {
            record.setStatus((short) 1);
        }
        progressRecordService.save(record);

        return "EYEYEY";
    }


}

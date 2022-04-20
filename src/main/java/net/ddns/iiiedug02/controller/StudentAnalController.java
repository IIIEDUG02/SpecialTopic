package net.ddns.iiiedug02.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import net.ddns.iiiedug02.model.service.StudentAnalService;

@Controller
@SessionAttributes({"classList"})
public class StudentAnalController {

    @Autowired
    private StudentAnalService analyService;

    @RequestMapping(path = "/inputmain", method = RequestMethod.GET)
    public String processMainAction(Model m) {
        List<Map<String, Integer>> classList = analyService.getClassList();

        m.addAttribute("classList", classList);

        return "success/MainPercentInput";
    }

    @GetMapping("/getgenderbyID")
    public String getGenderByID(@RequestParam("id") String id, Model m) {
        List<Map<String, Integer>> GenderList = analyService.getgenderbyID(id);

        Map<String, String> errors = new HashMap<String, String>();
        m.addAttribute("errors", errors);
        if (id == null || id.length() == 0) {
            errors.put("genderid", "請輸入課程ID");
        }
        if (errors != null && !errors.isEmpty()) {
            return "success/MainPercentInput";
        }

        if (GenderList != null && !GenderList.isEmpty()) {
            m.addAttribute("genderList", GenderList);
            return "success/GenderPage";
        }
        errors.put("gendermsg", "訂單中無此課程");
        return "success/MainPercentInput";
    }

    @GetMapping("/getAgePercentbyID")
    public String getAgePercentByID(@RequestParam("id") String id, Model m) {
        List<Map<String, Integer>> agePercentList = analyService.getAgePercentbyID(id);

        Map<String, String> errors = new HashMap<String, String>();
        m.addAttribute("errors", errors);
        if (id == null || id.length() == 0) {
            errors.put("ageid", "請輸入課程ID");
        }
        if (errors != null && !errors.isEmpty()) {
            return "success/MainPercentInput";
        }

        if (agePercentList != null && !agePercentList.isEmpty()) {
            m.addAttribute("agePercentList", agePercentList);
            return "success/AgePage";
        }
        errors.put("agemsg", "訂單中無此課程");
        return "success/MainPercentInput";
    }

    @GetMapping("/getJobPercentbyID")
    public String getJobPercentByID(@RequestParam("id") String id, Model m) {
        List<Map<String, Integer>> jobPercentList = analyService.getJobPercentbyID(id);

        Map<String, String> errors = new HashMap<String, String>();
        m.addAttribute("errors", errors);
        if (id == null || id.length() == 0) {
            errors.put("jobid", "請輸入課程ID");
        }
        if (errors != null && !errors.isEmpty()) {
            return "success/MainPercentInput";
        }

        if (jobPercentList != null && !jobPercentList.isEmpty()) {
            m.addAttribute("jobPercentList", jobPercentList);
            return "success/JobPage";
        }
        errors.put("jobmsg", "訂單中無此課程");
        return "success/MainPercentInput";
    }

    @GetMapping("/getMoney")
    public String getMoney(Model m) {
        List<Map<String, Integer>> moneyList2022 = analyService.getMoney();

        m.addAttribute("moneyList2022", moneyList2022);

        return "success/MoneyPage";
    }

    @GetMapping("/getAllData")
    public String getAllData(Model m) {
        List<Map<String, Integer>> mostjobList = analyService.orimostjob();
        TreeMap<Integer, String> mostJobMap = new TreeMap<Integer, String>();

        for (Map<String, Integer> jobMap : mostjobList) {
            if (!mostJobMap.containsKey(jobMap.get("cid"))) {
                mostJobMap.put(jobMap.get("cid"), String.valueOf(jobMap.get("job")));
            } else {
                mostJobMap.put(jobMap.get("cid"), mostJobMap.get(jobMap.get("cid")) + ","
                        + String.valueOf(jobMap.get("job")));
            }
        }
        m.addAttribute("mostjobMap", mostJobMap);

        List<Map<String, Integer>> mostgenderList = analyService.mostgender();
        TreeMap<Integer, String> mostGenderMap = new TreeMap<Integer, String>();

        for (Map<String, Integer> genderMap : mostgenderList) {
            if (!mostGenderMap.containsKey(genderMap.get("cid"))) {
                mostGenderMap.put(genderMap.get("cid"), String.valueOf(genderMap.get("gender")));
            } else {
                mostGenderMap.put(genderMap.get("cid"), "一樣多");
            }
        }
        List<String> mostGenderList = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : mostGenderMap.entrySet()) {
            mostGenderList.add(entry.getValue());
        }
        m.addAttribute("mostgenderList", mostGenderList);

        List<Map<String, Integer>> avgAgeList = analyService.avgAge();
        m.addAttribute("avgAgeList", avgAgeList);

        return "success/AllAnal";
    }
}

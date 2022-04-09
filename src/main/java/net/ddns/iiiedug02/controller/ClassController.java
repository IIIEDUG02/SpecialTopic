package net.ddns.iiiedug02.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.ddns.iiiedug02.exception.RoleNotFoundException;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.ClassManagementBean;
import net.ddns.iiiedug02.model.bean.ClassOnlineBean;
import net.ddns.iiiedug02.model.bean.CurriculumBean;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.ShoppingCart;
import net.ddns.iiiedug02.model.service.ClassBeanService;
import net.ddns.iiiedug02.model.service.ClassManagementService;
import net.ddns.iiiedug02.model.service.ClassOnlineService;
import net.ddns.iiiedug02.model.service.CurriculumService;
import net.ddns.iiiedug02.model.service.MemberService;
import net.ddns.iiiedug02.model.service.ShoppingCartService;
import net.ddns.iiiedug02.util.UniversalTool;

@Controller
@SessionAttributes({"classbean"})
public class ClassController {
    @Autowired
    private CurriculumService cus;

    @Autowired
    private ClassBeanService cbs;

    @Autowired
    private MemberService ms;

    @Autowired
    private ClassOnlineService cos;

    @Autowired
    private ClassManagementService cms;

    @Autowired
    private ShoppingCartService scs;

    @Autowired
    private UniversalTool utool;

    /*
     * 進入後臺管理課程，區分admin跟normal身份
     */
    @GetMapping("class/list")
    public String dispatch(HttpServletRequest request, Principal principal, Model model) {
        Member loginBean = ms.findByUsername(principal.getName());
        if (utool.hasRole(principal, "admin")) {
            List<ClassBean> cbList = cbs.findAll();
            model.addAttribute("allCbList", cbList);
            return "class/classList";
        } else if (utool.hasRole(principal, "normal")) {
            List<ClassManagementBean> cmbList = cms.findByUid(loginBean.getUid());
            List<ClassBean> completeList = new ArrayList<ClassBean>();
            List<ClassBean> uncompleteList = new ArrayList<ClassBean>();

            if (!cmbList.isEmpty()) {
                for (ClassManagementBean cmb : cmbList) {
                    ClassBean cb = cbs.findById(cmb.getCid());
                    if (cmb.getStatus() == 1) {
                        completeList.add(cb);
                    } else {
                        uncompleteList.add(cb);
                    }
                }
                model.addAttribute("uncompleteList", uncompleteList);
                model.addAttribute("completeList", completeList);
            }
            return "class/classList";
        }
        throw new RoleNotFoundException();
    }

    // 進入編輯課程頁面
    @GetMapping(value = "class/update/{cid}")
    public String updatePage(@PathVariable int cid, Model model) {
        ClassBean cb = cbs.findById(cid);
        model.addAttribute("classBean", cb);
        return "backstage/classEdit";
    }

    // 計算全部上線課程總數
    @ResponseBody
    @GetMapping(value = "/countclass")
    public int processCountClass() {
        return cbs.countClass();
    }

    // 課程圖片上傳 api
    @PostMapping(path = "class/api/uploadphoto")
    @ResponseBody
    public String uploadPhoto(@RequestParam("myPhoto") MultipartFile mf, HttpServletRequest request,
            Model m) throws IllegalStateException, IOException {

        String pattern = "yyyy-MM-dd-HH-mm-ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Random random = new Random();
        int rNumber = 10000 + random.nextInt(90000);
        // 取副檔名方法一
        // String oName = mf.getOriginalFilename();
        // int oNameLenghth = oName.length();
        // String fileName =
        // simpleDateFormat.format(new Date()) + "-" + rNumber +
        // oName.substring(oNameLenghth-4,
        // oNameLenghth);
        String type = FilenameUtils.getExtension(mf.getOriginalFilename());
        if (type.isEmpty()) {
            return "no image";
        }
        String fileName = simpleDateFormat.format(new Date()) + "-" + rNumber + "." + type;

        String tempDir = request.getSession().getServletContext().getRealPath("/")
                + "../resources/static/classphoto//";
        File tempDirFile = new File(tempDir);
        tempDirFile.mkdirs();

        String saveFilePath = tempDir + fileName;
        File saveFile = new File(saveFilePath);

        mf.transferTo(saveFile);

        m.addAttribute("photopath", "/SpecialTopic/classphoto/" + fileName);
        return "...";
    }

    // 尋找全部上線課程,api
    @GetMapping(path = "class/allonline")
    @ResponseBody
    public List<ClassBean> findAllOnlineClass() {
        List<ClassOnlineBean> coList = cos.findAll();
        List<ClassBean> cbList = new ArrayList<ClassBean>();
        for (ClassOnlineBean co : coList) {
            ClassBean cb = cbs.findById(co.getCid());
            cb.setClassDetailsBean(null);
            cb.setCurriculumbean(null);
            cbList.add(cb);
        }
        return cbList;
    }

    @GetMapping("class/curriculums/{cid}")
    public String classtest(Model m, @PathVariable("cid") int cid) {
        ClassBean cb = cbs.findById(cid);
        List<CurriculumBean> cusList = cus.findAllByClassbean(cb);
        m.addAttribute("CurriculumList", cusList);
        m.addAttribute("cid", cid);
        return "class/curriculum-nilm";
    }

    @GetMapping("class/curriculums/api/{cid}")
    @ResponseBody
    public List<CurriculumBean> getCurListJson(Model m, @PathVariable("cid") int cid) {
        ClassBean cb = cbs.findById(cid);
        return cus.findAllByClassbean(cb);
    }

    @DeleteMapping("class/delete/{cid}")
    @ResponseBody
    public boolean deleteClass(HttpServletRequest request, Principal principal,
            @PathVariable("cid") int cid) {
        if (utool.hasRole(principal, "admin")) {
            return cbs.deleteById(cid);
        }
        return false;
    }

    @GetMapping("class/editCurriculum/{cid}")
    public String editCurriculum(HttpServletRequest request, Principal principal,
            @PathVariable("cid") int cid, Model m) {
        if (utool.hasRole(principal, "admin")) {
            ClassBean cb = cbs.findById(cid);
            List<CurriculumBean> cubList = cus.findAllByClassbean(cb);
            m.addAttribute("cubList", cubList);
            return "class/editCurriculum";
        } else {
            m.addAttribute("errMsg", "您無權限執行此操作");
            return "index";
        }

    }

    @GetMapping("viewClass/{cid}")
    public String viewClass(@PathVariable("cid") int cid, Model m, Principal p,
            RedirectAttributes attr) {


        // 課程資訊
        ClassBean cb = cbs.findById(cid);
        if (cb == null) {
            attr.addAttribute("msg", "找不到課程資料");
            return "redirect:/class/list";
        }
        m.addAttribute("classBean", cb);

        if (p != null) {
            Member loginBean = ms.findByUsername(p.getName());
            // 個人購課紀錄
            ClassManagementBean cmb = cms.findByUidAndCid(loginBean.getUid(), cid);
            if (null != cmb) {
                m.addAttribute("classManagerBean", cmb);
            } else {
                // 購課車
                ShoppingCart sc = scs.findByUidAndClassBean(loginBean.getUid(), cb);
                m.addAttribute("ShoppingCart", sc);
            }
        }

        return "class/viewClass";
    }

}

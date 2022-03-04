// package net.ddns.iiiedug02.controller.demo;
//
// import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.ResponseBody;
// import net.ddns.iiiedug02.model.bean.MemberBean;
// import net.ddns.iiiedug02.model.service.MemberService;
//
//
// @Controller
// public class MemberController {
//
// @Autowired
// private MemberService ms;
//
// // @Autowired
// // private SqlDateUtil sqlDateUtil;
//
// @ResponseBody
// @GetMapping("memberFindById")
// public MemberBean memberFindById() {
// return ms.findById(1);
// }
//
// @ResponseBody
// @GetMapping("memberFindAll")
// public List<MemberBean> memberFindAll() {
// return ms.findAll();
// }
//
// @ResponseBody
// @GetMapping("memberFindByUsername/{username}")
// public String memberFindByUsername(@PathVariable("username") String username) {
// return ms.findByUsername(username).toString();
// }
// }

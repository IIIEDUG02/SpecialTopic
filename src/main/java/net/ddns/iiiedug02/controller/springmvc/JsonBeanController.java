package net.ddns.iiiedug02.controller.springmvc;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.ddns.iiiedug02.model.beans.MemberBean;

@Controller
public class JsonBeanController {

  @ResponseBody
  @RequestMapping(path = "beanToJson", method = RequestMethod.GET)
  public String beanToJson() throws JsonProcessingException {
    MemberBean m1 = new MemberBean();
    m1.setActivated((short) 1).setPassword("123").setRoles("no").setUsername("48da4").setUid(0);
    ObjectMapper om1 = new ObjectMapper();
    String memberJsonStr = om1.writeValueAsString(m1);

    MemberBean resultBean = om1.readValue(memberJsonStr, MemberBean.class);
    System.out.println(resultBean);

    return memberJsonStr;
  }



  @ResponseBody
  @RequestMapping(path = "beanToJson2", method = RequestMethod.GET)
  public MemberBean beanToJson2() throws JsonProcessingException {

    MemberBean m2 = new MemberBean();
    m2.setActivated((short) 1).setPassword("123").setRoles("no").setUsername("48da4").setUid(0);

    return m2;
  }


  @ResponseBody
  @RequestMapping(path = "beanListToJson", method = RequestMethod.GET)
  public List<MemberBean> beanListToJson() throws JsonProcessingException {

    MemberBean m2 = new MemberBean();
    m2.setActivated((short) 1).setPassword("123").setRoles("no").setUsername("48da4").setUid(0);

    List<MemberBean> l1 = new ArrayList<MemberBean>();
    l1.add(m2);
    l1.add(m2);
    l1.add(m2);
    return l1;
  }
}

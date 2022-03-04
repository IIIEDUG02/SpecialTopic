package net.ddns.iiiedug02.controller.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import net.ddns.iiiedug02.model.bean.demo.DataBaseProperties;

@RestController
public class DataBaseController {

  @Autowired
  private DataBaseProperties dbp;


  @GetMapping("dbp")
  public DataBaseProperties dbpAction() {
    return dbp;
  }
}

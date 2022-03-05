package net.ddns.iiiedug02.model.service.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.ddns.iiiedug02.model.bean.demo.Picture;
import net.ddns.iiiedug02.model.dao.demo.PictureDAO;

@Transactional
@Service
public class PictureService {

  @Autowired
  private PictureDAO pdao;

  public Picture upload(Picture picture) {
    return pdao.upload(picture);
  }
}

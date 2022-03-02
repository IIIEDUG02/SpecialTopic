package net.ddns.iiiedug02.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.ddns.iiiedug02.model.beans.Picture;
import net.ddns.iiiedug02.model.daos.PictureDAO;

@Transactional
@Service
public class PictureService {

  @Autowired
  private PictureDAO pdao;

  public Picture upload(Picture picture) {
    return pdao.upload(picture);
  }
}

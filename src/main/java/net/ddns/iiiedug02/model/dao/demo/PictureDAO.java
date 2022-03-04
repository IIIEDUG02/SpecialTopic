package net.ddns.iiiedug02.model.dao.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import net.ddns.iiiedug02.model.bean.demo.Picture;

@Transactional
@Repository
public class PictureDAO {

  @Autowired
  private SessionFactory sessionFactory;


  public Picture upload(Picture picture) {
    Session session = sessionFactory.openSession();
    if (picture != null) {
      session.save(picture);
      session.close();
      return picture;
    }
    session.close();
    return null;

  }
}

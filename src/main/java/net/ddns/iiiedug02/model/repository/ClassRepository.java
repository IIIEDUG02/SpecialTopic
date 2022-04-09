package net.ddns.iiiedug02.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import net.ddns.iiiedug02.model.bean.ClassBean;

public interface ClassRepository extends JpaRepository<ClassBean, Integer> {

  public List<ClassBean> findAllByUid(Integer Uid);


  @Query(value = "select count(*) from class", nativeQuery = true)
  public int countClass();

  @Query(value = "select distinct class_type from class", nativeQuery = true)
  public List<String> findAllClassType();
  
  public List<ClassBean> findByClassType(String class_type);
  
  public List<ClassBean> findAllByCid(Integer Cid);
}

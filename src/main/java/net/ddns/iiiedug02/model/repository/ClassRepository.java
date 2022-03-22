package net.ddns.iiiedug02.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import net.ddns.iiiedug02.model.bean.ClassBean;

public interface ClassRepository extends JpaRepository<ClassBean, Integer> {

  public List<ClassBean> findAllByUid(Integer Uid);

  @Query(value = "select class_type from class", nativeQuery = true)
  public List<ClassBean> findAllClassType();

  @Query(value = "select count(*) from class", nativeQuery = true)
  public int countClass();
}

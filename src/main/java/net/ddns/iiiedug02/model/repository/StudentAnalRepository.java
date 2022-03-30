package net.ddns.iiiedug02.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import net.ddns.iiiedug02.model.bean.StudentAnalysis;

public interface StudentAnalRepository extends JpaRepository<StudentAnalysis, Integer> {

  @Override
  @Query(value = "select * from studentAnalysis", nativeQuery = true)
  public List<StudentAnalysis> findAll();


}

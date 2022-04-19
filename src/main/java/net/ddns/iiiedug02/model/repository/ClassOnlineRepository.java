package net.ddns.iiiedug02.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import net.ddns.iiiedug02.model.bean.ClassOnlineBean;

public interface ClassOnlineRepository extends JpaRepository<ClassOnlineBean, Integer> {

    @Query(value = "select * from class_online where online = 1", nativeQuery = true)
    public List<ClassOnlineBean> findAllOnlineClass();



}

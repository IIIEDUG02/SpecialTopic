package net.ddns.iiiedug02.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ddns.iiiedug02.model.bean.YPclass;


public interface YPclassRepository extends JpaRepository<YPclass, Integer> {
	
	@Query(value = "select * from ypclass where priority is not null order by priority", nativeQuery = true)
	public List<YPclass> findAll();
	
	@Query(value = "update ypclass set priority =1 where classID = ?1", nativeQuery = true)
	public void updateypclass(String priority);
	
	@Query(value = "select * from ypclass", nativeQuery = true)
	public List<YPclass> findAllRow();
}

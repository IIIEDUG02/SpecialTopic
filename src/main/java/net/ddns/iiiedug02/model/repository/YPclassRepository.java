package net.ddns.iiiedug02.model.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ddns.iiiedug02.model.bean.YPclass;


public interface YPclassRepository extends JpaRepository<YPclass, Integer> {
	
	@Query(value = "select * from ypclass where priority is not null order by priority", nativeQuery = true)
	public List<YPclass> findAll();
	
	@Transactional
    @Modifying
	@Query(value = "update ypclass set priority =?1 where classID = ?2", nativeQuery = true)
	public void updateypclass(String priority, String classid);
	
	@Transactional
    @Modifying
	@Query(value = "update ypClass set priority = null", nativeQuery = true)
	public void resetypclass();
	
	@Query(value = "select * from ypclass", nativeQuery = true)
	public List<YPclass> findAllRow();
}

package net.ddns.iiiedug02.model.repository;

import java.util.List;
import java.util.Map;

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
	
	@Query(value = "select * from ypclass order by yearAmount DESC, classID", nativeQuery = true)
	public List<YPclass> findAllRow();
	
	@Query(value = "select top (3) * from ypclass order by yearAmount DESC, classID", nativeQuery = true)
	public List<YPclass> findRow();
	
	@Query(value = "select title, photo from class where cid = ?1", nativeQuery = true)
	public List<Map<String, String>> findPicByID(int cid);
}

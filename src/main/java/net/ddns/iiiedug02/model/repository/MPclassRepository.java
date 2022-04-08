package net.ddns.iiiedug02.model.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ddns.iiiedug02.model.bean.MPclass;


public interface MPclassRepository extends JpaRepository<MPclass, Integer> {
	
	@Query(value = "select * from mpclass where priority is not null order by priority", nativeQuery = true)
	public List<MPclass> findAll();
	
	@Transactional
    @Modifying
	@Query(value = "update mpclass set priority =?1 where classID = ?2", nativeQuery = true)
	public void updatempclass(String priority, String classid);
	
	@Transactional
    @Modifying
	@Query(value = "update mpClass set priority = null", nativeQuery = true)
	public void resetmpclass();
	
	@Query(value = "select * from mpclass order by monthAmount DESC, classID", nativeQuery = true)
	public List<MPclass> findAllRow();
	
	@Query(value = "select top (3) * from mpclass order by monthAmount DESC, classID", nativeQuery = true)
	public List<MPclass> findRow();
	
}

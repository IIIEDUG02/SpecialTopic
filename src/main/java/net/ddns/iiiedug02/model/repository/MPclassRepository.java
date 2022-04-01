package net.ddns.iiiedug02.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ddns.iiiedug02.model.bean.MPclass;
import net.ddns.iiiedug02.model.bean.YPclass;


public interface MPclassRepository extends JpaRepository<MPclass, Integer> {
	
	@Query(value = "select * from mpclass", nativeQuery = true)
	public List<MPclass> findAll();
	
	
}

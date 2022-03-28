package net.ddns.iiiedug02.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ddns.iiiedug02.model.bean.MPteacher;


public interface MPteacherRepository extends JpaRepository<MPteacher, Integer> {
	
	@Query(value = "select * from mpteacher", nativeQuery = true)
	public List<MPteacher> findAll();
	
	
}

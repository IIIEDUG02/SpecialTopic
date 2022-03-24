package net.ddns.iiiedug02.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ddns.iiiedug02.model.bean.YPteacher;

public interface YPteacherRepository extends JpaRepository<YPteacher, Integer> {
	
	@Query(value = "select * from ypteacher", nativeQuery = true)
	public List<YPteacher> findAll();
	
	
}

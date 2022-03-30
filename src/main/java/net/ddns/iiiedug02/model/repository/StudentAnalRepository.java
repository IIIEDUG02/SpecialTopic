package net.ddns.iiiedug02.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ddns.iiiedug02.model.bean.StudentAnalysis;
import net.ddns.iiiedug02.model.bean.YPclass;


public interface StudentAnalRepository extends JpaRepository<StudentAnalysis, Integer> {
	
	@Query(value = "select * from studentAnalysis", nativeQuery = true)
	public List<StudentAnalysis> findAll();
	
	
}

package net.ddns.iiiedug02.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ddns.iiiedug02.model.bean.ClassBean;


public interface ClassRepository extends JpaRepository<ClassBean, Integer> {

}

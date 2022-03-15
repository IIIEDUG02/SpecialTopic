package net.ddns.iiiedug02.model.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ddns.iiiedug02.model.bean.ClassDetailsBean;


public interface ClassDetailsRepository extends JpaRepository<ClassDetailsBean, Integer> {

}

package net.ddns.iiiedug02.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ddns.iiiedug02.model.bean.ClassManagementBean;

public interface ClassManagementRepository extends JpaRepository<ClassManagementBean, Integer> {

}

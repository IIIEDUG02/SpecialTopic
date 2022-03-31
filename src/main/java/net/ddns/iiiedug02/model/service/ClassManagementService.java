package net.ddns.iiiedug02.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ddns.iiiedug02.model.bean.ClassManagementBean;
import net.ddns.iiiedug02.model.repository.ClassManagementRepository;

@Service
@Transactional
public class ClassManagementService {
	@Autowired
	private ClassManagementRepository cmRepo;
	
	public ClassManagementBean insert(ClassManagementBean cmb) {
		return cmRepo.save(cmb);
	}
	
	public ClassManagementBean update(ClassManagementBean cmb) {
		return cmRepo.save(cmb);
	}
}

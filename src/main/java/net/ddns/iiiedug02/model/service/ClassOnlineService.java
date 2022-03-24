package net.ddns.iiiedug02.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ddns.iiiedug02.model.bean.ClassOnlineBean;
import net.ddns.iiiedug02.model.repository.ClassOnlineRepository;

@Service
@Transactional
public class ClassOnlineService {
	
	@Autowired
	private ClassOnlineRepository coRepo;
	
	public ClassOnlineBean insert(ClassOnlineBean cob) {
		return coRepo.save(cob);
	}
	
	public ClassOnlineBean update(ClassOnlineBean cob) {
		return coRepo.save(cob);
	}
	
	public List<ClassOnlineBean> findAll() {
		return coRepo.findAll();
	}
	
}	

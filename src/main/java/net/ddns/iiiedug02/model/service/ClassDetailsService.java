package net.ddns.iiiedug02.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ddns.iiiedug02.model.bean.ClassDetailsBean;
import net.ddns.iiiedug02.model.interfaces.ClassDetailsRepository;


@Service
@Transactional
public class ClassDetailsService {
	@Autowired
    private ClassDetailsRepository cdRepo;
	
	
	public ClassDetailsBean insert(ClassDetailsBean cdb) {
		return cdRepo.save(cdb);
	}
	
	public ClassDetailsBean update(ClassDetailsBean cdb) {
		return cdRepo.save(cdb);
	}
	
	public void deleteById(Integer id) {
		cdRepo.deleteById(id);
	}
	public ClassDetailsBean findById(Integer id) {
		Optional<ClassDetailsBean> op1 = cdRepo.findById(id);
		
		if(op1.isEmpty()) {
			return null;
		}
		
		return op1.get();
	}
}

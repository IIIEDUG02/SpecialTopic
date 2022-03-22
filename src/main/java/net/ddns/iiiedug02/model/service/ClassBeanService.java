package net.ddns.iiiedug02.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.repository.ClassRepository;



@Service
@Transactional
public class ClassBeanService {
	
	@Autowired
    private ClassRepository cRepo;
	
	
	public ClassBean insert(ClassBean cb) {
		return cRepo.save(cb);
	}
	
	public ClassBean update(ClassBean cb) {
		return cRepo.save(cb);
	}
	
	public void deleteById(Integer id) {
		cRepo.deleteById(id);
	}
	public ClassBean findById(Integer id) {
		Optional<ClassBean> op1 = cRepo.findById(id);
		
		if(op1.isEmpty()) {
			return null;
		}
		
		return op1.get();
	}
	
	public List<ClassBean> findAllByUid(Integer uid){
		return cRepo.findAllByUid(uid);
	}
	public List<ClassBean> findAllClassType(){
		return cRepo.findAllClassType();
	}
	public int countClass(){
		return cRepo.countClass();
	}
}
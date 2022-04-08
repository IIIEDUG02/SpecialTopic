package net.ddns.iiiedug02.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ddns.iiiedug02.model.bean.YPclass;
import net.ddns.iiiedug02.model.repository.YPclassRepository;


@Service
@Transactional
public class YPclassService {
	
	@Autowired
	private YPclassRepository YPRepo;
	
	public YPclass findById(Integer id) {
		Optional<YPclass> op1 = YPRepo.findById(id);
		
		if(op1.isEmpty()) {
			return null;
		}
		return op1.get();
		
	}
	
	public List<YPclass> findAll(){
		return YPRepo.findAll();
	}
	
	public List<YPclass> findAllRow(){
		return YPRepo.findAllRow();
	}
	
	public List<YPclass> findRow(){
		return YPRepo.findRow();
	}
	
	public void  insert(YPclass yp){
		YPRepo.save(yp);
	}
	
	public void updateypclass(String priority, String classid){
		YPRepo.updateypclass(priority, classid);
	}
	
	public void resetypclass(){
		YPRepo.resetypclass();
	}
}

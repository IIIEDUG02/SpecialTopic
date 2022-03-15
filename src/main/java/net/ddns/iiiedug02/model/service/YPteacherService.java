package net.ddns.iiiedug02.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ddns.iiiedug02.model.bean.YPteacher;
import net.ddns.iiiedug02.model.repository.YPteacherRepository;

@Service
@Transactional
public class YPteacherService {
	
	@Autowired
	private YPteacherRepository YPRepo;
	
	public YPteacher findById(Integer id) {
		Optional<YPteacher> op1 = YPRepo.findById(id);
		
		if(op1.isEmpty()) {
			return null;
		}
		return op1.get();
		
	}
	
	public List<YPteacher> findAll(){
		return YPRepo.findAll();
	}
	
	public void  insert(YPteacher yp){
		YPRepo.save(yp);
	}
}

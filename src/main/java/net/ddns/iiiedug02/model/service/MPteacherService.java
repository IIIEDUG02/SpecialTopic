package net.ddns.iiiedug02.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ddns.iiiedug02.model.bean.MPteacher;

import net.ddns.iiiedug02.model.repository.MPteacherRepository;


@Service
@Transactional
public class MPteacherService {
	
	@Autowired
	private MPteacherRepository mpRepo;
	
	public MPteacher findById(Integer id) {
		Optional<MPteacher> op1 = mpRepo.findById(id);
		
		if(op1.isEmpty()) {
			return null;
		}
		return op1.get();
		
	}
	
	public List<MPteacher> findAll(){
		return mpRepo.findAll();
	}
	
	public void  insert(MPteacher mp){
		mpRepo.save(mp);
	}
}

package net.ddns.iiiedug02.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ddns.iiiedug02.model.bean.MPclass;
import net.ddns.iiiedug02.model.bean.YPclass;
import net.ddns.iiiedug02.model.repository.MPclassRepository;
import net.ddns.iiiedug02.model.repository.YPclassRepository;


@Service
@Transactional
public class MPclassService {
	
	@Autowired
	private MPclassRepository mPRepo;
	
	public MPclass findById(Integer id) {
		Optional<MPclass> op1 = mPRepo.findById(id);
		
		if(op1.isEmpty()) {
			return null;
		}
		return op1.get();
		
	}
	
	public List<MPclass> findAll(){
		return mPRepo.findAll();
	}
	
	public void  insert(MPclass mp){
		mPRepo.save(mp);
	}
}

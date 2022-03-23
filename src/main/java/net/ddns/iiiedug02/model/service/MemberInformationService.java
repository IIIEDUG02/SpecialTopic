package net.ddns.iiiedug02.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ddns.iiiedug02.model.bean.MemberInformation;

import net.ddns.iiiedug02.model.repository.MemberInformationRepository;


@Service
@Transactional
public class MemberInformationService {
	
	@Autowired
	private MemberInformationRepository meminfoRepo;
	
	public MemberInformation findByUid(Integer uid) {
		Optional<MemberInformation> op1 = meminfoRepo.findByUid(uid);
		
		if(op1.isEmpty()) {
			return null;
		}
		return op1.get();
		
	}
	
	public List<MemberInformation> findAll(){
		return meminfoRepo.findAll();
	}
	
	
}

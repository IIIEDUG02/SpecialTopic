package net.ddns.iiiedug02.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ddns.iiiedug02.model.bean.CurriculumBean;
import net.ddns.iiiedug02.model.repository.CurriculumRepository;


@Service
@Transactional
public class CurriculumService {
	@Autowired
    private CurriculumRepository cuRepo;
	
	
	public CurriculumBean insert(CurriculumBean cub) {
		return cuRepo.save(cub);
	}
	
	public CurriculumBean update(CurriculumBean cub) {
		return cuRepo.save(cub);
	}
	
	public void deleteById(Integer id) {
		cuRepo.deleteById(id);
	}
	public CurriculumBean findById(Integer id) {
		Optional<CurriculumBean> op1 = cuRepo.findById(id);
		
		if(op1.isEmpty()) {
			return null;
		}
		
		return op1.get();
	}
}

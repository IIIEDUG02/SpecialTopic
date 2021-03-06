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

    public boolean deleteById(Integer id) {
        try {
            cRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public ClassBean findById(Integer id) {
        Optional<ClassBean> op1 = cRepo.findById(id);

        if (op1.isEmpty()) {
            return null;
        }

        return op1.get();
    }

    public List<ClassBean> findAllByUid(Integer uid) {
        return cRepo.findAllByUid(uid);
    }

    public List<String> findAllClassType() {
        return cRepo.findAllClassType();
    }
    public List<ClassBean> findByClassType(String class_type) {
    	return cRepo.findByClassType(class_type);
    }

    public int countClass() {
        return cRepo.countClass();
    }

    public List<ClassBean> findAll() {
        return cRepo.findAll();
    }

}

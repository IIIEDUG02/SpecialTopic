package net.ddns.iiiedug02.model.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.ddns.iiiedug02.model.bean.MPclass;
import net.ddns.iiiedug02.model.repository.MPclassRepository;

@Service
@Transactional
public class MPclassService {

    @Autowired
    private MPclassRepository mPRepo;

    public MPclass findById(Integer id) {
        Optional<MPclass> op1 = mPRepo.findById(id);

        if (op1.isEmpty()) {
            return null;
        }
        return op1.get();

    }

    public List<MPclass> findAll() {
        return mPRepo.findAll();
    }

    public List<MPclass> findAllRow() {
        return mPRepo.findAllRow();
    }

    public List<MPclass> findRow() {
        return mPRepo.findRow();
    }

    public void insert(MPclass mp) {
        mPRepo.save(mp);
    }

    public void updatempclass(String priority, String classid) {
        mPRepo.updatempclass(priority, classid);
    }

    public void resetmpclass() {
        mPRepo.resetmpclass();
    }
}

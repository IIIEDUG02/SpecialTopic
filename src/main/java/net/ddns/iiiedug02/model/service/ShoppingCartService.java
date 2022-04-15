package net.ddns.iiiedug02.model.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.ddns.iiiedug02.model.bean.ClassBean;
import net.ddns.iiiedug02.model.bean.ShoppingCart;
import net.ddns.iiiedug02.model.repository.ShoppingCartRepository;

@Service
/*
 * 購物車清單的Service
 * 
 * @author Nilm
 */
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> findAllByUid(int uid) {
        return shoppingCartRepository.findByUid(uid);
    }

    public List<Integer> findIdListByUid(int uid) {
        List<ShoppingCart> scl = shoppingCartRepository.findByUid(uid);
        List<Integer> scidl = new ArrayList<Integer>();
        for (ShoppingCart sc : scl) {
            scidl.add(sc.getId());
        }
        return scidl;
    }

    public ShoppingCart findByUidAndClassBean(int uid, ClassBean cb) {
        return shoppingCartRepository.findByUidAndClassBean(uid, cb);
    }

    public void deleteById(int id) {
        shoppingCartRepository.deleteById(id);
    }

    public ShoppingCart save(ShoppingCart sc) {
        if (null == shoppingCartRepository.findByUidAndClassBean(sc.getUid(), sc.getClassBean())) {
            shoppingCartRepository.save(sc);
            return shoppingCartRepository.findByUidAndClassBean(sc.getUid(), sc.getClassBean());
        }
        return null;
    }

    public void deleteByList(List<ShoppingCart> scl) {
        shoppingCartRepository.deleteAll(scl);
    }

    public void deleteByUidAndClassBean(ShoppingCart sc) {
        if (null == shoppingCartRepository.findByUidAndClassBean(sc.getUid(), sc.getClassBean())) {
            shoppingCartRepository.deleteByUidAndClassBean(sc.getUid(), sc.getClassBean());
        }
    }
}

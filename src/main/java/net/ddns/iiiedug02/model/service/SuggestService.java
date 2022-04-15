package net.ddns.iiiedug02.model.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.ddns.iiiedug02.model.bean.Suggest;
import net.ddns.iiiedug02.model.repository.SuggestRepository;


@Service
public class SuggestService {

    @Autowired
    private SuggestRepository suggestRepository;

    public Suggest findById(int cid) {
        Optional<Suggest> sug = suggestRepository.findById(cid);
        if (null != sug) {
            return sug.get();
        }
        return null;
    }

    public List<Suggest> findAll() {
        return suggestRepository.findAll();
    }

    public List<Suggest> findByStatus(short status) {
        return suggestRepository.findByStatus(status);
    }

    public void save(Suggest sug) {
        suggestRepository.save(sug);
    }

}
